package Service.View;

import java.util.List;

public class TextComparatorView {

    public void printMisspellings(List<List<String>> misspellings){
        for (List<String> words : misspellings){
            System.out.print(words.get(1) + " -> " + words.get(0) + ", ");
        }
        System.out.println("\n");
    }

    public void printPercent(double percent){
        System.out.println(percent + "%");
    }

}
