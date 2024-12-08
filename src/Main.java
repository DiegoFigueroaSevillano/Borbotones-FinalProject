import Service.Controller.TextComparatorController;
import Service.Utils.TextReader;

public class Main {
    public static void main(String[] args) {

        TextComparatorController controller = new TextComparatorController();
        TextReader reader = new TextReader();
        String original = "C:\\Users\\diego\\IdeaProjects\\BorbotonesGroup-Project\\Assets\\Texts\\original.txt";
        String copy = "C:\\Users\\diego\\IdeaProjects\\BorbotonesGroup-Project\\Assets\\Texts\\copy.txt";

        String originalContent = reader.readTextFromFile(original);
        String copyContent = reader.readTextFromFile(copy);

        System.out.println(originalContent);

        controller.compare(originalContent.toLowerCase(), copyContent.toLowerCase());

    }
}