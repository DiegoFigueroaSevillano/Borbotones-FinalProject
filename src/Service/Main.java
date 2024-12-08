package Service;

import Service.Controller.BorbotonesSystemController;

public class Main {
    public static void main(String[] args) {
        BorbotonesSystemController controller = new BorbotonesSystemController(8080);
        controller.run();
    }
}
