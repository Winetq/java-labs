package sample;

import javafx.concurrent.Task;

import java.io.*;
import java.net.Socket;

public class SendFileTask extends Task {
    File file; // plik do wyslania
    private int bytes;
    private ObjectOutputStream oos;
    private String fileName;

    public SendFileTask(File file, String name) {
        this.file = file;
        this.bytes = (int)file.length();
        this.fileName = name;
    }

    @Override
    protected Object call() throws Exception {
        if (bytes <= 1024*1024) { // max 1MB
            // initiating
            updateMessage("Initiating...");
            updateProgress(0, bytes);
            Thread.sleep(1000); // 1s

            // sending
            updateMessage("Sending...");
            try (Socket client = new Socket("localhost", 8080)) { // try-with-resources
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                     DataOutputStream dos = new DataOutputStream(client.getOutputStream())) {
                    dos.writeUTF(fileName); // wyslanie nazwy pliku jpg
                    dos.writeInt(bytes); // wyslanie liczby bajtow

                    byte[] buffer = new byte[4096]; // bufor 4KB

                    int read_size = 0;
                    while (read_size != bytes) {
                        int read = in.read(buffer);
                        dos.write(buffer, 0, read); // wysylanie bufora 4KB
                        read_size += read;
                        updateProgress(read_size, bytes);
                        Thread.sleep(100); // 100ms
                    }
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }

            // finishing
            updateMessage("Finishing...");
            Thread.sleep(1000); // 1s

            // refreshing
            updateMessage("Label!");
            updateProgress(0, 1);
        } else {
            updateMessage("Label!");
            updateProgress(0, 1);
            System.out.println("Max size is 1MB!");
        }

        return null;
    }
}
