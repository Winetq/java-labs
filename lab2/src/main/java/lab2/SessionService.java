package lab2;

import java.util.ArrayList;
import java.util.List;

public class SessionService {
    private int n;
    private List<Thread> threads;
    TasksResource tasks_resource;
    ResultsResource results_resource;

    public SessionService(int n) {
        this.n = n;
        threads = new ArrayList<>();
        tasks_resource = new TasksResource();
        results_resource = new ResultsResource();
    }

    public void start() {
        for (int i = 1; i <= n; i++) {
            threads.add(new Thread(new Solver(tasks_resource, results_resource)));
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void end() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {}
        }
    }

    public TasksResource getTasksResource() {
        return tasks_resource;
    }

    public ResultsResource getResultsResource() {
        return results_resource;
    }
}
