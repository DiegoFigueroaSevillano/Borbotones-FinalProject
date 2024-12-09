package Client.Model;

import Server.Model.TextComparatorModel;

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

    public static class ResultEntry {
        private final String taskName;
        private final TextComparatorModel result;
        private final long duration;

        public ResultEntry(String taskName, TextComparatorModel result, long duration) {
            this.taskName = taskName;
            this.result = result;
            this.duration = duration;
        }

        public String getTaskName() {
            return taskName;
        }

        public TextComparatorModel getResult() {
            return result;
        }

        public long getDuration() {
            return duration;
        }
    }
}
