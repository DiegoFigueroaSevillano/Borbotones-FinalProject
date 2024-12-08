package Client.Model;

import java.io.Serializable;
import java.util.List;

public class TextComparatorModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private double percentage;
    private List<List<String>> misspellings;
    private String originalText, copyText;

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

    @Override
    public String toString() {
        return "TextComparatorModel{" +
                "percentage=" + percentage +
                ", misspellings=" + misspellings +
                ", originalText='" + originalText + '\'' +
                ", copyText='" + copyText + '\'' +
                '}';
    }
}
