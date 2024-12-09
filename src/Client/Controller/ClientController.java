package Client.Controller;

import Client.SocketManagement.SocketHandler;
import Client.View.ClientView;
import Server.Model.TextComparatorModel;

import java.io.IOException;

public class ClientController {
    private final String host;
    private final int port;
    public final ClientView view;
    private TextComparatorModel model;

    public ClientController(String host, int port) {
        this.host = host;
        this.port = port;
        this.view = new ClientView();
    }

    public void run() {
        boolean continueRunning = true;

        while (continueRunning) {
            try (SocketHandler socketHandler = new SocketHandler(host, port)) {
                handleFileComparison(socketHandler);
            } catch (IOException | ClassNotFoundException e) {
                view.showErrorMessage(e.getMessage());
            }

            continueRunning = askUserToContinue();
        }

        view.showExitMessage();
    }

    private void handleFileComparison(SocketHandler socketHandler) throws IOException, ClassNotFoundException {
        String originalTextPath = view.originalTextRequestMessage();
        String compareTextPath = view.compareTextRequestMessage();

        sendPathsToServer(socketHandler, originalTextPath, compareTextPath);
        model = receiveResultsFromServer(socketHandler);

        showResults();
    }

    private void sendPathsToServer(SocketHandler socketHandler, String originalPath, String comparePath) {
        try {
            socketHandler.send(originalPath);
            socketHandler.send(comparePath);
        } catch (IOException e) {
            view.showErrorMessage("Failed to send paths to server: " + e.getMessage());
        }
    }

    private TextComparatorModel receiveResultsFromServer(SocketHandler socketHandler) throws IOException, ClassNotFoundException {
        return (TextComparatorModel) socketHandler.readObject();
    }

    private void showResults() {
        view.percentageMessage(model.getPercentage());
        view.misspellingsMessage(model.getMisspellings());
    }

    private boolean askUserToContinue() {
        String userInput = view.askContinueMessage();
        return userInput.equalsIgnoreCase("yes");
    }
}
