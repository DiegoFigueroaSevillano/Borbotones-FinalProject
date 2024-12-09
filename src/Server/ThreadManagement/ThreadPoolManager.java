package Server.ThreadManagement;

import java.util.concurrent.*;

public class ThreadPoolManager {
    private ExecutorService executorService;

    public ThreadPoolManager(int poolSize) {
        executorService = Executors.newFixedThreadPool(poolSize);
    }

    public void submitTask(Runnable task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }

        if (((ThreadPoolExecutor) executorService).getQueue().size() > 100) { // Límite ejemplo
            System.err.println("Task queue is full. Task rejected.");
            return;
        }

        executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}
