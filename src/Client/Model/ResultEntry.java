package Client.Model;


import Server.Model.TextComparatorModel;

public class ResultEntry {
    private final String taskName;
    private final Server.Model.TextComparatorModel result;
    private final long duration;

    public ResultEntry(String taskName, Server.Model.TextComparatorModel result, long duration) {
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