package Client;


import Client.SocketManagement.SocketHandler;

import java.io.IOException;
import java.net.Socket;

public class Main {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int port = 8080;

        try {
            Socket clientSocket = new Socket(host, port);
            SocketHandler socketHandler = new SocketHandler(clientSocket);
            socketHandler.send("Hello from Client!");
            String response = socketHandler.listen();
            System.out.println("Server response: " + response);
            socketHandler.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
