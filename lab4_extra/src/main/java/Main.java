import lab4.Browar;
import lab4.JPAManager;
import lab4.JPAManagerBrowar;
import lab4.Piwo;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgMC");

        JPAManager<Piwo, String> managerPiwa = new JPAManager<>(emf, Piwo.class);
        JPAManagerBrowar managerBrowara = new JPAManagerBrowar(emf, Browar.class);

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.print("command: ");
            String command = scanner.next();
            System.out.println();
            switch (command) {
                case "add":
                    System.out.println("Co chcesz dodac? Piwo/Browar");
                    String odp = scanner.next();
                    if (odp.equals("Browar")) {
                        System.out.print("Podaj name dla browara: ");
                        String name = scanner.next();
                        System.out.print("Podaj wartosc browara: ");
                        long value = scanner.nextLong();
                        scanner.nextLine(); // pobranie znaku enter
                        Browar browar = managerBrowara.find(name); // sprawdzam czy przypadkiem taki browar juz nie istnieje
                        if (browar == null)
                            managerBrowara.add(new Browar(name, value)); // nie istnieje czyli mozna dodac nowy browar
                        else
                            System.out.println("Browar o takim id juz istnieje w bazie!");
                        System.out.println();
                    } else if (odp.equals("Piwo")) {
                        System.out.print("Do jakiego browara nalezy to piwo: ");
                        String id = scanner.next();
                        Browar browar  = managerBrowara.find(id);
                        if (browar != null) {
                            System.out.print("Podaj name dla piwa: ");
                            String name = scanner.next();
                            System.out.print("Podaj cene za piwo: ");
                            long price = scanner.nextLong();
                            scanner.nextLine(); // pobranie znaku enter
                            Piwo temp = managerPiwa.find(name); // sprawdzam czy przypadkiem takie piwo juz nie istnieje
                            if (temp == null) {
                                Piwo piwo = new Piwo(name, price, browar); // nie istnieje czyli tworze takie piwo
                                managerPiwa.add(piwo); // dodanie piwa do bazy
                                browar.addPiwo(piwo); // dodanie piwa do tego browara
                                managerBrowara.update(browar); // zaktualizowanie browara
                            } else {
                                System.out.println("Piwo o takim id juz istnieje!");
                            }
                            System.out.println();
                        } else {
                            System.out.println("Nie ma takiego browara!");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Unknown command!");
                        System.out.println();
                    }
                    break;
                case "delete":
                    System.out.println("Co chcesz usunac? Piwo/Browar");
                    String what = scanner.next();
                    if (what.equals("Browar")) {
                        System.out.print("Podaj name: ");
                        String id = scanner.next();
                        Browar browar = managerBrowara.find(id);
                        if (browar != null) {
                            if (browar.getSize() == 0) {
                                managerBrowara.delete(browar);
                                System.out.println();
                            } else {
                                System.out.println("Usun najpierw wszystkie piwa!");
                                System.out.println();
                            }
                        } else {
                            System.out.println("Nie ma takiego browara!");
                            System.out.println();
                        }
                    } else if (what.equals("Piwo")) {
                        System.out.print("Podaj id: ");
                        String id = scanner.next();
                        Piwo piwo = managerPiwa.find(id);
                        if (piwo != null) {
                            managerPiwa.delete(piwo);
                            System.out.println();
                        } else {
                            System.out.println("Nie ma takiego piwa!");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Unknown command!");
                        System.out.println();
                    }
                    break;
                case "show":
                    System.out.println("Wszystkie piwa:");
                    managerPiwa.show();
                    System.out.println("Wszystkie browary:");
                    managerBrowara.show();
                    System.out.println();
                    break;
                case "queries": // zapytanie z zadania 5
                    System.out.print("Podaj cene: ");
                    int k = scanner.nextInt();
                    scanner.nextLine(); // pobranie znaku enter
                    List<Browar> browary = managerBrowara.findAllBrowaryThatHavePiwaCheaperThen(k);
                    System.out.println("Wszystkie browary, ktore maja piwa tansze niz " + k + ":");
                    if (browary.size() == 0) {
                        System.out.println("BRAK");
                    } else {
                        for (Browar x : browary) {
                            System.out.println(x);
                        }
                    }
                    System.out.println();
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("add/delete/show/queries/quit");
                    System.out.println();
            }
        }

        emf.close();
    }

}
