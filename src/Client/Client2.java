package Client;

import Client.Controller.BorbotonesClientController;

public class Client2 {

    public static void run() {
        BorbotonesClientController controller = new BorbotonesClientController("127.0.0.1", 8080);

        String originalPath = "/home/samuel-escalera/Documents/Borbotones-FinalProject/Assets/Texts/Client2/original.txt";
        String copyPath = "/home/samuel-escalera/Documents/Borbotones-FinalProject/Assets/Texts/Client2/copy.txt";

        int count = 0;

        while (true) {
            try {
                System.out.println("Client2." + count);
                controller.run(originalPath, copyPath);
                count++;
                System.out.println("----------------------------------");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.err.println("Client was interrupted: " + e.getMessage());
                break;
            }
        }
    }
    public static void main(String[] args) {
        Client2.run();
    }
}
