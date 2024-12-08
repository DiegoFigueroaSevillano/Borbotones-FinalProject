package Service.Repository;

import Service.Algorithm.LCSAlgorithm;

import java.util.*;

public class TextComparatorRepository {

    LCSAlgorithm algorithm;

    public TextComparatorRepository() {
        this.algorithm = new LCSAlgorithm();
    }

    public List<List<String>> getMisspelling(Map<List<List<String>>, Integer> basicPlagiarismResult){
        List<List<String>> misspellings = new ArrayList<>();
        for (List<List<String>> key : basicPlagiarismResult.keySet()){
            List<String> originalRest =  getDifferentWords(key.get(0), key.get(1));
            List<String> copyRest = getDifferentWords(key.get(1), key.get(0));
            int maxLCS = 0;
            int auxLCS;
            int auxIndex = 0;
            for (String originalWord : originalRest){
                for (int i = 0; i < copyRest.size(); i++){
                    auxLCS = algorithm.LCS(originalWord, copyRest.get(i));
                    if (auxLCS > maxLCS){
                        maxLCS = auxLCS;
                        auxIndex = i;
                    }
                }

                if (((double) maxLCS /originalWord.length()) * 100 > 55.0 && ((double) maxLCS /copyRest.get(auxIndex).length()) * 100 > 55.0){
                    misspellings.add(new ArrayList<>(Arrays.asList(originalWord, (copyRest.get(auxIndex)))));
                    basicPlagiarismResult.put(key, basicPlagiarismResult.get(key) + 1);
                }
                maxLCS = 0;
                auxIndex = 0;
            }
        }
        return misspellings;
    }


    public Map<List<List<String>>, Integer> getMaxLCS(List<List<String>> originalOrations, List<List<String>> copyOrations){
        Map<List<List<String>>, Integer> result = new HashMap<>();
        int maxLCS = 0;
        int auxLCS;
        int auxIndex = 0;
        for (int i = 0; i < copyOrations.size(); i++){
            for (int j = 0; j < originalOrations.size(); j++){
                auxLCS = algorithm.LCS(copyOrations.get(i), originalOrations.get(j));
                if (auxLCS > maxLCS){
                    maxLCS = auxLCS;
                    auxIndex = j;
                }
            }
            List<List<String>> aux = new ArrayList<>();
            aux.add(originalOrations.get(auxIndex));
            aux.add(copyOrations.get(i));
            result.put(aux, maxLCS);
            maxLCS = 0;
        }

        return result;
    }

    public List<String> getDifferentWords(List<String> oration, List<String> without){
        Set<String> firstSet = new HashSet<>(oration);
        Set<String> secondSet = new HashSet<>(without);
        firstSet.removeAll(secondSet);

        return new ArrayList<>(firstSet);
    }

    public double getTotalOfWords(List<List<String>> orations){
        int total = 0;
        for (List<String> oration : orations){
            total += oration.size();
        }
        return total;
    }

    public double getPercent(Map<List<List<String>>, Integer> plagiarismResults, double totalWords){
        double totalPlagiarismWords = 0;
        for (Integer value : plagiarismResults.values()) {
            totalPlagiarismWords += value;
        }
        return ((totalPlagiarismWords / totalWords) * 100.0);
    }
}
