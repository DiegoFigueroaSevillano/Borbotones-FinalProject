package Client.View;

import Client.Model.ResultStore;

import java.util.List;

public class ClientView {

    public String getFilePath() {
        try {
            javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
            fileChooser.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                @Override
                public boolean accept(java.io.File f) {
                    return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                }

                @Override
                public String getDescription() {
                    return "Text Files (*.txt)";
                }
            });

            int result = fileChooser.showOpenDialog(null);
            if (result == javax.swing.JFileChooser.APPROVE_OPTION) {
                return fileChooser.getSelectedFile().getAbsolutePath();
            } else {
                showNoFileSelectedMessage();
                return null;
            }
        } catch (Exception e) {
            showErrorMessage("File chooser not supported. Using console fallback.");
            return fallbackConsoleInput();
        }
    }

    public String originalTextRequestMessage() {
        showRequestMessage("Press enter to select the original file:");
        waitForEnter();
        return getFilePath();
    }

    public String compareTextRequestMessage() {
        showRequestMessage("Press enter to select the text to compare:");
        waitForEnter();
        return getFilePath();
    }

    public void percentageMessage(double percentage) {
        System.out.println("The similarity percentage is: " + percentage);
    }

    public void misspellingsMessage(List<List<String>> misspellings) {
        System.out.println("The words that were misspelled are: " + misspellings);
    }

    public String askContinueMessage() {
        System.out.println("Do you want to compare another pair of files? (yes to continue, any other key to exit)");
        return getUserInput();
    }

    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    public void showExitMessage() {
        System.out.println("Exiting the application. Goodbye!");
    }

    private void showRequestMessage(String message) {
        System.out.println(message);
    }

    private void showNoFileSelectedMessage() {
        System.out.println("No file selected. Exiting...");
    }

    private String fallbackConsoleInput() {
        return new java.util.Scanner(System.in).nextLine();
    }

    private void waitForEnter() {
        try {
            System.in.read();
        } catch (java.io.IOException e) {
            showErrorMessage("Failed to wait for Enter key.");
        }
    }

    private String getUserInput() {
        System.out.print("> ");
        return new java.util.Scanner(System.in).nextLine();
    }

    public void showSummary(ResultStore resultStore) {
        System.out.println("\nSummary of executed tasks:");
        System.out.println("=============================");
        resultStore.getResults().forEach(result -> {
            System.out.println("Task: " + result.getTaskName());
            System.out.println("Similarity Percentage: " + result.getResult().getPercentage() + "%");
            System.out.println("Misspellings: " + result.getResult().getMisspellings());
            System.out.println("Duration: " + result.getDuration() + " ms");
            System.out.println("-----------------------------");
        });
    }
}
