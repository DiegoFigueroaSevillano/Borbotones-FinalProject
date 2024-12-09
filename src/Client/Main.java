package Client;

import Client.Controller.ClientController;

public class Main {

    public static void main(String[] args) {
        ClientController controller = new ClientController("127.0.0.1", 8080);
        controller.run();
    }
}
