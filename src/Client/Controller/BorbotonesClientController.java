package Client.Controller;

import Client.SocketManagement.SocketHandler;
import Service.Model.TextComparatorModel;

import java.io.IOException;

public class BorbotonesClientController {
    private String host;
    private int port;

    public BorbotonesClientController(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run(String originalPath, String copyPath) {
        try (SocketHandler socketHandler = new SocketHandler(host, port)) {
            System.out.println("Connected to server.");

            socketHandler.send(originalPath);
            socketHandler.send(copyPath);
            System.out.println("Paths sent to server.");

            TextComparatorModel result = (TextComparatorModel) socketHandler.readObject();
            System.out.println("Received result from server:");
            System.out.println("Percentage: " + result.getPercentage());
            System.out.println("Misspellings: " + result.getMisspellings());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
