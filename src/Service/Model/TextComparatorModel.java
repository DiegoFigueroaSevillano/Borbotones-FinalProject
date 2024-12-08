package Service.Model;

import java.util.List;

public class TextComparatorModel {

    double percentage;
    List<List<String>> misspellings;
    String originalText, copyText;

    public TextComparatorModel(String originalText, String copyText, double percentage, List<List<String>> misspellings) {
        this.originalText = originalText;
        this.copyText = copyText;
        this.percentage = percentage;
        this.misspellings = misspellings;
    }

    public double getPercentage() {
        return percentage;
    }

    public List<List<String>> getMisspellings() {
        return misspellings;
    }

    public String getOriginalText() {
        return originalText;
    }

    public String getCopyText() {
        return copyText;
    }
}
