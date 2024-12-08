package Service;


import Service.SocketManagement.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        int port = 8080;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected");
                SocketHandler socketHandler = new SocketHandler(clientSocket);
                String message = socketHandler.listen();
                System.out.println("Received from client: " + message);
                socketHandler.send("Hello from Server!");
                socketHandler.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
