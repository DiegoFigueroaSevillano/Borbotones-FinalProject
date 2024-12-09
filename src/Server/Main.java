package Server;

import Server.Controller.ServerController;

public class Main {
    public static void main(String[] args) {
        int port = 8080;
        int poolSize = 10;

        ServerController controller = new ServerController(port, poolSize);
        controller.run();
    }
}
