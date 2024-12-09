package Server.ThreadManagement;

import java.util.concurrent.*;

public class ThreadPoolManager {
    private ExecutorService executorService;

    public ThreadPoolManager(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void submitTask(Runnable task) {
        executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
