package Client.Model;

import Server.Model.TextComparatorModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultStore {
    private final List<ResultEntry> results = new ArrayList<>();

    public synchronized void addResult(String taskName, TextComparatorModel result, long duration) {
        results.add(new ResultEntry(taskName, result, duration));
    }

    public synchronized List<ResultEntry> getResults() {
        return Collections.unmodifiableList(results);
    }

    public synchronized void generateSummary(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Task Summary:\n");
            writer.write("=============================\n");
            for (ResultEntry entry : results) {
                writer.write("Task: " + entry.getTaskName() + "\n");
                writer.write("Percentage: " + entry.getResult().getPercentage() + "\n");
                writer.write("Misspellings: " + entry.getResult().getMisspellings() + "\n");
                writer.write("Duration: " + entry.getDuration() + "ms\n");
                writer.write("-----------------------------\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing summary file: " + e.getMessage());
        }
    }
}
