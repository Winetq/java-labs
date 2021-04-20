package lab2;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultsResource { // sekcja krytyczna
    private Map<Long, List<Long>> results;

    public ResultsResource() {
        this.results = new HashMap<>();
    }

    public synchronized void save(long number,  List<Long> divisors) {
        results.put(number, divisors);
    }

    public void show() {
        try {
            FileWriter writer = new FileWriter("results.txt");
            for (Object x : results.keySet()) {
                writer.write(x + ": ");
                int n = results.get(x).size();
                if (n != 0) {
                    long lastValue = results.get(x).get(n - 1);
                    for (Object y : results.get(x)) {
                        if (lastValue == (long)y)
                            writer.write(y + "");
                        else
                            writer.write(y + ", ");
                    }
                    writer.write("\n");
                } else {
                    writer.write("\n");
                }
            }
            writer.close();
            System.out.println("Results are in the results.txt!");
        } catch (IOException error) {
            System.out.println("error w write()");
        }
    }
}
