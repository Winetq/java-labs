package lab2;

import java.util.ArrayList;
import java.util.List;

public class Solver implements Runnable {
    private TasksResource tasks_resource;
    private ResultsResource results_resource;

    public Solver(TasksResource tasks_resource, ResultsResource results_resource) {
        this.tasks_resource = tasks_resource;
        this.results_resource = results_resource;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                long number = tasks_resource.take();
                List<Long> divisors = new ArrayList<>();

                int counter = 0;
                for (long i = 1; i <= number; i++) {
                    if (number % i == 0)
                        divisors.add(i);
                }

                results_resource.save(number, divisors);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
