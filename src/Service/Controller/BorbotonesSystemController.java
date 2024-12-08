package Service.Controller;

import Service.Model.TextComparatorModel;
import Service.Service.TextComparatorService;
import Service.SocketManagement.SocketHandler;
import Service.Utils.TextReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BorbotonesSystemController {
    private int port;
    private TextComparatorService service = new TextComparatorService();
    private TextReader textReader = new TextReader();
    int clientCount = 0;

    public BorbotonesSystemController(int port) {
        this.port = port;
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                Thread clientHandler = new Thread(() -> handleClient(clientSocket));
                clientHandler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try (SocketHandler socketHandler = new SocketHandler(clientSocket)) {

            String originalPath = socketHandler.listen();
            String copyPath = socketHandler.listen();
            System.out.println("Received paths: " + originalPath + " and " + copyPath);

            String originalContent = textReader.readTextFromFile(originalPath);
            String copyContent = textReader.readTextFromFile(copyPath);

            TextComparatorModel result = service.compare(originalContent, copyContent);

            socketHandler.writeObject(result);
            System.out.println("Result sent to client:" + clientCount);
            clientCount++;
        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}
