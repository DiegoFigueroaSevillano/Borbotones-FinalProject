package Client;

import Client.Controller.BorbotonesClientController;

public class Main {
    public static void main(String[] args) {
        BorbotonesClientController controller = new BorbotonesClientController("127.0.0.1", 8080);
        controller.run();
    }
}
