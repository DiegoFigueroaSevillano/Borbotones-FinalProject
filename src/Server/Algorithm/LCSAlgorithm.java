package Server.Algorithm;

import java.util.List;

public class LCSAlgorithm {

    public int LCS(List<String> firstOration, List<String> secondOration){

        int[][] dp = new int[firstOration.size() + 1][secondOration.size() + 1];

        for (int i = 1; i <= firstOration.size(); i++){
            for (int j = 1; j <= secondOration.size(); j++){
                if (firstOration.get(i - 1).equals(secondOration.get(j - 1))){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[firstOration.size()][secondOration.size()];
    }


    public int LCS(String firstWord, String secondWord){

        int[][] dp = new int[firstWord.length() + 1][secondWord.length() + 1];

        for (int i = 1; i <= firstWord.length(); i++){
            for (int j = 1; j <= secondWord.length(); j++){
                if (firstWord.charAt(i - 1) == (secondWord.charAt(j - 1))){
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp[firstWord.length()][secondWord.length()];
    }

}
