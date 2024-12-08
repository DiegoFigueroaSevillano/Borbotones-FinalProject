package Client;

import Client.Controller.BorbotonesClientController;

public class Client1 {

    public static void run() {
        BorbotonesClientController controller = new BorbotonesClientController("127.0.0.1", 8080);

        String originalPath = "/home/samuel-escalera/Documents/Borbotones-FinalProject/Assets/Texts/Client1/original.txt";
        String copyPath = "/home/samuel-escalera/Documents/Borbotones-FinalProject/Assets/Texts/Client1/copy.txt";

        int count = 0;

        while (true) {
            try {
                System.out.println("Client1." + count);
                controller.run(originalPath, copyPath);
                count++;
                System.out.println("----------------------------------");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                System.err.println("Client was interrupted: " + e.getMessage());
                break;
            }
        }
    }

    public static void main(String[] args) {
        Client1.run();
    }
}
