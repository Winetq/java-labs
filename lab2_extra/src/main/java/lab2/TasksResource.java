package lab2;

import java.util.ArrayList;
import java.util.List;

public class TasksResource { // mechanizm wait-notify
    private List<Long> tasks;

    public TasksResource() {
        this.tasks = new ArrayList<>();
    }

    public synchronized long take() throws InterruptedException { // InterruptedException jest obslugiwany w klasie Solver w metodzie run()
        while (tasks.isEmpty()) {
            wait();
        }

        return tasks.remove(0); // zwracamy pierwsze zadanie do wykonania
    }

    public synchronized void put(List<Long> new_tasks) {
        tasks.addAll(new_tasks);
        notifyAll();
    }
}
