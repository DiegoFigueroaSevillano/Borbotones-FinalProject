package Server.Task;

import Server.Model.TextComparatorModel;
import Server.Service.TextComparatorService;
import Server.Utils.TextReader;

import java.util.List;

public class TextComparasionTask implements Runnable {
    private String originalPath;
    private String copyPath;
    private TextComparatorService service;
    private TextReader textReader;
    private TaskResultCallback callback;

    public TextComparasionTask(String originalPath, String copyPath, TaskResultCallback callback) {
        this.originalPath = originalPath;
        this.copyPath = copyPath;
        this.service = new TextComparatorService();
        this.textReader = new TextReader();
        this.callback = callback;
    }

    @Override
    public void run() {
        try {
            String originalContent = textReader.readTextFromFile(originalPath);
            String copyContent = textReader.readTextFromFile(copyPath);

            if (originalContent == null || originalContent.trim().isEmpty()) {
                System.err.println("Original file is empty or unreadable.");
                callback.onTaskComplete(new TextComparatorModel("", "", 0, List.of()));
                return;
            }

            if (copyContent == null || copyContent.trim().isEmpty()) {
                System.err.println("Copy file is empty or unreadable.");
                callback.onTaskComplete(new TextComparatorModel("", "", 0, List.of()));
                return;
            }

            TextComparatorModel result = service.compare(originalContent, copyContent);
            callback.onTaskComplete(result);

        } catch (Exception e) {
            System.err.println("Error processing task: " + e.getMessage());
        }
    }

}
