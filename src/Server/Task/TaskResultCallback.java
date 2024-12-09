package Server.Task;

import Server.Model.TextComparatorModel;

public interface TaskResultCallback {
    void onTaskComplete(TextComparatorModel result);
}
