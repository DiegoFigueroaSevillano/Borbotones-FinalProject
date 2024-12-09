package Server.Controller;

import Server.Model.TextComparatorModel;
import Server.Service.TextComparatorService;
import Server.SocketManagement.SocketHandler;
import Server.Task.TaskResultCallback;
import Server.Task.TextComparasionTask;
import Server.ThreadManagement.ThreadPoolManager;
import Server.Utils.TextReader;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerController {
    private int port;
    private TextComparatorService service = new TextComparatorService();
    private TextReader textReader = new TextReader();
    private int clientCount = 0;
    private ThreadPoolManager threadPoolManager;

    public ServerController(int port, int poolSize) {
        this.port = port;
        this.threadPoolManager = new ThreadPoolManager(poolSize);
    }

    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                threadPoolManager.submitTask(() -> handleClient(clientSocket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket clientSocket) {
        try {
            SocketHandler socketHandler = new SocketHandler(clientSocket);

            String originalPath = socketHandler.listen();
            String copyPath = socketHandler.listen();
            System.out.println("Received paths: " + originalPath + " and " + copyPath);

            TextComparasionTask task = new TextComparasionTask(originalPath, copyPath, new TaskResultCallback() {
                @Override
                public void onTaskComplete(TextComparatorModel result) {
                    try {
                        socketHandler.writeObject(result);
                        System.out.println("Result sent to client: " + clientCount);
                        clientCount++;
                    } catch (IOException e) {
                        System.err.println("Error sending result to client: " + e.getMessage());
                    } finally {
                        try {
                            socketHandler.close();
                        } catch (IOException e) {
                            System.err.println("Error closing socket: " + e.getMessage());
                        }
                    }
                }
            });

            threadPoolManager.submitTask(task);

        } catch (IOException e) {
            System.err.println("Error handling client: " + e.getMessage());
        }
    }
}
