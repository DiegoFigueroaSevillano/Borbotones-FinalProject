package Server.Service;

import Server.Model.TextComparatorModel;
import Server.Repository.TextComparatorRepository;
import Server.Utils.StringUtils;

import java.util.List;
import java.util.Map;

public class TextComparatorService {

    StringUtils stringUtils;
    TextComparatorRepository repository;

    public TextComparatorService() {
        stringUtils = new StringUtils();
        repository = new TextComparatorRepository();
    }

    public TextComparatorModel compare(String originalText, String copyText) {
        List<List<String>> originalOrations = stringUtils.split(originalText);
        List<List<String>> copyOrations = stringUtils.split(copyText);

        Map<List<List<String>>, Integer> map = repository.getMaxLCS(originalOrations, copyOrations);
        List<List<String>> misspellings = repository.getMisspelling(map);
        double percent = repository.getPercent(map, repository.getTotalOfWords(copyOrations));

        return new TextComparatorModel(originalText, copyText, percent, misspellings);
    }

}
