import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class IncomingSocketConnectionHandler implements Runnable {
    private final ServerSocket server;

    public IncomingSocketConnectionHandler(int port) {
        try {
            this.server = new ServerSocket(port);
            server.setSoTimeout(1000); // 1s
            } catch (IOException error) {
            throw new IllegalStateException(error);
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try  {
                Socket socket = server.accept();
                new Thread(new SocketConnectionHandler(socket)).start();
            } catch (IOException error) {}
        }
        close_server();
    }

    public void close_server() {
        try {
            server.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
}
