package lab2;

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
                int number = tasks_resource.take();
                boolean isPrime;

                int counter = 0;
                for (int i = 1; i <= number; i++) {
                    if (number % i == 0)
                        counter += 1;
                }

                isPrime = counter == 2;

                results_resource.save(number, isPrime);
            } catch (InterruptedException ex) {
                break;
            }
        }
    }
}
