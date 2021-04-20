import lab2.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // zadanie: podanie dzielnikow danych liczb
        int n = Integer.parseInt(args[0]); // liczba watkow
        Scanner scanner = new Scanner(System.in);
        SessionService session_service = new SessionService(n);
        session_service.start();
        session_service.getInitialTasks();

        int odp = 1;
        String text;
        while (odp != 2) {
            System.out.println("1. dodaj zadania");
            System.out.print("Wybierz 1/exit: ");
            text = scanner.nextLine();
            if (text.equals("exit"))
                odp = 2;
            else if (text.equals("1"))
                odp = 1;
            else
                odp = 3;

            switch (odp) {
                case 1:
                    System.out.print("Liczba zadan: ");
                    int number_of_tasks = scanner.nextInt();
                    scanner.nextLine(); // pobranie znaku enter
                    List<Long> numbers = new ArrayList<>(number_of_tasks);
                    for (int i = 0; i < number_of_tasks; i++) {
                        System.out.print("Podaj liczbe: ");
                        long x = scanner.nextLong();
                        scanner.nextLine();
                        if (x >= 0)
                            numbers.add(x);
                    }

                    System.out.println(); // znak enter
                    session_service.getTasksResource().put(numbers);
                    break;
                case 2:
                    session_service.end();
                    break;
                default:
                    System.out.println("Nieznana cyfra!");
                    System.out.println(); // znak enter
            }
        }

        session_service.getResultsResource().show(); // zapisanie wynikow w results.txt
    }

}
