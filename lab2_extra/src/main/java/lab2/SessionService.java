package lab2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SessionService {
    private int n;
    private List<Thread> threads;
    private TasksResource tasks_resource;
    private ResultsResource results_resource;

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

    public void getInitialTasks() {
        try {
            Scanner reader = new Scanner(new FileReader("numbers.txt")); // w folderze projektu
            List<Long> tasks = new ArrayList<>();
            while (reader.hasNextLine()) {
                String number = reader.nextLine();
                long x = Long.parseLong(number);
                if (x >= 0)
                    tasks.add(x);
            }
            this.tasks_resource.put(tasks);
            reader.close();
        } catch (FileNotFoundException error) {
            System.out.println("error w read()");
        }
    }

    public TasksResource getTasksResource() {
        return tasks_resource;
    }

    public ResultsResource getResultsResource() {
        return results_resource;
    }
}
