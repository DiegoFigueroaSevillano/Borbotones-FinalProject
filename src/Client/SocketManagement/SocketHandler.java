package Client.SocketManagement;

import java.io.*;
import java.net.Socket;

public class SocketHandler {
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;


    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new PrintWriter(socket.getOutputStream(), true);
    }


    public void send(String message) {
        writer.println(message);
    }

    public String listen() throws IOException {
        return reader.readLine();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
