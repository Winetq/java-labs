package lab2;

import java.util.HashMap;
import java.util.Map;

public class ResultsResource { // sekcja krytyczna
    private Map<Integer, Boolean> results;

    public ResultsResource() {
        this.results = new HashMap<>();
    }

    public synchronized void save(int number, boolean isPrime) {
        results.put(number, isPrime);
    }

    public void show() {
        for (Object x : results.keySet()) {
            System.out.println(x + " " + results.get(x));
        }
    }
}
