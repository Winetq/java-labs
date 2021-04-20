import java.util.Scanner;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server!");

        Scanner scanner = new Scanner(System.in);
        Thread serverThread = new Thread(new IncomingSocketConnectionHandler(8080));
        System.out.println("Starting HTTP listener.");
        serverThread.start();

        boolean running = true;

        while (running) {
            System.out.print("command: ");
            String command = scanner.next();
            switch (command) {
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("an unknown command");
            }
        }

        serverThread.interrupt();
        System.out.println("Stopping HTTP listener.");
    }

}
