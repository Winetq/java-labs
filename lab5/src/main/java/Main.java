import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        MageRepository mageRepository = new MageRepository();
        MageController mageController = new MageController(mageRepository);

        Scanner scanner = new Scanner(System.in);

        boolean running = true;

        while (running) {
            System.out.print("command: ");
            String command = scanner.next();
            switch (command) {
                case "find":
                    System.out.print("Podaj id: ");
                    String name = scanner.next();
                    System.out.println(mageController.find(name));
                    break;
                case "delete":
                    System.out.print("Podaj id: ");
                    String id = scanner.next();
                    System.out.println(mageController.delete(id));
                    break;
                case "save":
                    System.out.print("Podaj id: ");
                    String ID = scanner.next();
                    System.out.print("Podaj level: ");
                    int level = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println(mageController.save(ID, level));
                    break;
                case "show":
                    mageController.showMageRepository();
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("find|delete|save|show|quit");
            }
        }
    }

}
