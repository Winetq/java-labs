package lab2;

import java.util.ArrayList;
import java.util.List;

public class TasksResource { // mechanizm wait-notify
    private List<Integer> tasks;

    public TasksResource() {
        this.tasks = new ArrayList<>();
    }

    public synchronized int take() throws InterruptedException { // exception is served in the class Solver in the method run()
        while (tasks.isEmpty()) {
            wait();
        }

        return tasks.remove(0); // zwracamy pierwsze zadanie do wykonania
    }

    public synchronized void put(List<Integer> new_tasks) {
        tasks.addAll(new_tasks);
        notifyAll();
    }
}
