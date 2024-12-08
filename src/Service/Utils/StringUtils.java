package Service.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtils {

    private List<List<String>> splitOration(List<String> text){
        List<List<String>> result = new ArrayList<>();
        for (String oration : text){
            result.add(Arrays.asList(oration.trim().split(" ")));
        }
        return result;
    }


    private List<String> splitText(String text){
        return Arrays.asList(text.trim().split("\\."));
    }


    public List<List<String>> split(String text){
        return splitOration(splitText(text));
    }

}
