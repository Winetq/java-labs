import java.io.*;
import java.net.Socket;

public class SocketConnectionHandler implements Runnable {
    private final Socket socket;

    public SocketConnectionHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (DataInputStream dis = new DataInputStream(socket.getInputStream());
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(dis.readUTF() + ".jpg")))) {
            int size = dis.readInt();

            byte[] buffer = new byte[4096];
            int read_size = 0;
            while (read_size != size) {
                int read = dis.read(buffer);
                out.write(buffer, 0, read);
                read_size += read;
            }

            System.out.println(); // znak enter
            System.out.println("JPG image is saved!!!");
            System.out.print("command: ");
        } catch (IOException ex) {
            System.out.println("error in deserialization");
        }
        close_socket();
    }

    public void close_socket() {
        try {
            socket.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }
}
