package Server.SocketManagement;

import java.io.*;
import java.net.Socket;

public class SocketHandler implements AutoCloseable {
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public SocketHandler(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public String listen() throws IOException {
        return reader.readLine();
    }

    public void send(String message) throws IOException {
        writer.write(message);
        writer.newLine();
        writer.flush();
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
        return objectInputStream.readObject();
    }

    public void writeObject(Object object) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
    }

    public void close() throws IOException {
        reader.close();
        writer.close();
        socket.close();
    }
}
