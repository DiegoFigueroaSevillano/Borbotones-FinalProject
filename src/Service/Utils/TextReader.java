package Service.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TextReader {

    public String readTextFromFile(String absolutePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(absolutePath)));
        } catch (IOException e) {
            return "Error leyendo el archivo: " + e.getMessage();
        }
    }
}
