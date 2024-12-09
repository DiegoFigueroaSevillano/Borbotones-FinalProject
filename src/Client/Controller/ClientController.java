package Client.Controller;

import Client.Model.ResultStore;
import Client.SocketManagement.SocketHandler;
import Client.View.ClientView;
import Server.Model.TextComparatorModel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientController {
    private final String host;
    private final int port;
    public final ClientView view;
    private final ResultStore resultStore;

    public ClientController(String host, int port) {
        this.host = host;
        this.port = port;
        this.view = new ClientView();
        this.resultStore = new ResultStore();
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

        generateSummaryAndExit();
    }

    private void handleFileComparison(SocketHandler socketHandler) throws IOException, ClassNotFoundException {
        String originalTextPath = view.originalTextRequestMessage();
        String compareTextPath = view.compareTextRequestMessage();

        long startTime = System.currentTimeMillis();

        sendPathsToServer(socketHandler, originalTextPath, compareTextPath);
        TextComparatorModel model = receiveResultsFromServer(socketHandler);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        resultStore.addResult("Comparison Task", model, duration);

        showResults(model);
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

    private void showResults(TextComparatorModel model) {
        view.percentageMessage(model.getPercentage());
        view.misspellingsMessage(model.getMisspellings());
    }

    private boolean askUserToContinue() {
        String userInput = view.askContinueMessage();
        return userInput.equalsIgnoreCase("yes");
    }

    private void generateSummaryAndExit() {
        view.showSummary(resultStore);
        saveSummaryToFile();
        view.showExitMessage();
    }

    private void saveSummaryToFile() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String fileName = "Summary_" + timestamp + ".txt";
        resultStore.generateSummary(fileName);
        System.out.println("Summary saved to file: " + fileName);
    }
}
