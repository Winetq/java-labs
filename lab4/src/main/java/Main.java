import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean initialize = args.length == 1 && Boolean.parseBoolean(args[0]);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("rpgMC");
        JPAManagerMage managerMage = new JPAManagerMage(emf, Mage.class);
        JPAManagerTower managerTower = new JPAManagerTower(emf, Tower.class);

        Scanner scanner = new Scanner(System.in);

        if (initialize) {
            InitializeTestData itd = new InitializeTestData(managerMage, managerTower);
            itd.init();
        }

        boolean running = true;

        while (running) {
            System.out.print("command: ");
            String command = scanner.next();
            System.out.println();
            switch (command) {
                case "add":
                    System.out.println("Co chcesz dodac? Tower/Mage");
                    String odp = scanner.next();
                    if (odp.equals("Tower")) {
                        System.out.print("Podaj name dla Tower: ");
                        String name = scanner.next();
                        Tower tower = managerTower.find(name); // spr czy Tower o takim id istnieje
                        if (tower == null)
                            managerTower.add(new Tower(name)); // dodanie Tower do bazy
                        else
                            System.out.println("Tower o takim id juz istnieje w bazie!");
                        System.out.println();
                    } else if (odp.equals("Mage")) {
                        System.out.print("Na jakiej wysokosci Tower chcesz dodac Mage: ");
                        int h = scanner.nextInt();
                        scanner.nextLine(); // pobranie znaku enter
                        Tower tower  = managerTower.findByHeight(h); // szukam Tower dla danego h
                        if (tower != null) {
                            System.out.print("Podaj name dla Mage: ");
                            String name = scanner.next();
                            System.out.print("Podaj level dla Mage: ");
                            int level = scanner.nextInt();
                            scanner.nextLine(); // pobranie znaku enter
                            Mage temp = managerMage.find(name); // spr czy Mage o takim id istnieje
                            if (temp == null) {
                                Mage mage = new Mage(name, level, tower);
                                managerMage.add(mage); // dodanie Mage do bazy
                                tower.addMage(mage); // dodanie Mage do tego Tower
                                managerTower.update(tower); // zaktualizowanie Tower
                            } else {
                                System.out.println("Mage o takim id juz istnieje!");
                            }
                            System.out.println();
                        } else {
                            System.out.println("Nie ma takiego Tower!");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Unknown command!");
                        System.out.println();
                    }
                    break;
                case "delete":
                    System.out.println("Co chcesz usunac? Tower/Mage");
                    String who = scanner.next();
                    if (who.equals("Tower")) {
                        System.out.print("Podaj id: ");
                        String id = scanner.next();
                        Tower tower = managerTower.find(id);
                        if (tower != null) {
                            if (tower.getSize() == 0) {
                                tower.decrementHeight(); // dekrementacja wysokosci wiezy
                                managerTower.delete(tower);
                                System.out.println();
                            } else {
                                System.out.println("Usun najpierw wszystkich Mage dla tego Tower!");
                                System.out.println();
                            }
                        } else {
                            System.out.println("Nie ma takiego Tower!");
                            System.out.println();
                        }
                    } else if (who.equals("Mage")) {
                        System.out.print("Podaj id: ");
                        String id = scanner.next();
                        Mage mage = managerMage.find(id);
                        if (mage != null) {
                            managerMage.delete(mage);
                            System.out.println();
                        } else {
                            System.out.println("Nie ma takiego Mage!");
                            System.out.println();
                        }
                    } else {
                        System.out.println("Unknown command!");
                        System.out.println();
                    }
                    break;
                case "show":
                    System.out.println("Mages:");
                    managerMage.show();
                    System.out.println("Towers:");
                    managerTower.show();
                    System.out.println();
                    break;
                case "queries":
                    System.out.print("Podaj level: ");
                    int lvl = scanner.nextInt();
                    scanner.nextLine(); // pobranie znaku enter
                    System.out.print("Podaj id Tower: ");
                    String id = scanner.next();
                    List<Mage> mages = managerMage.findAllMagesWithLevelAboveOrEqualAndWithSpecificTower(lvl, managerTower.find(id));
                    if (mages.size() != 0) {
                        for (Mage x : mages) {
                            System.out.println(x);
                        }
                    } else {
                        System.out.println("Niespelnione wymagania!");
                    }
                    System.out.println();
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("add/delete/show/quit/queries");
                    System.out.println();
            }
        }

        emf.close();
    }

}
