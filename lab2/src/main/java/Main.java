import lab2.SessionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) { // zadanie: spr czy liczba jest pierwsza
        int n = Integer.parseInt(args[0]); // number of threads
        Scanner scanner = new Scanner(System.in);
        SessionService session_service = new SessionService(n);
        session_service.start();

        int odp = 1;
        while (odp != 2) {
            System.out.println("1. dodaj zadania");
            System.out.println("2. koniec");
            System.out.print("Wybierz: ");
            odp = scanner.nextInt();

            switch (odp) {
                case 1:
                    System.out.print("Liczba zadan: ");
                    int number_of_tasks = scanner.nextInt();
                    List<Integer> numbers = new ArrayList<>(number_of_tasks);
                    for (int i = 0; i < number_of_tasks; i++) {
                        System.out.print("Podaj liczbe: ");
                        int x = scanner.nextInt();
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

        session_service.getResultsResource().show(); // show results
    }

}
