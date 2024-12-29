import java.util.Arrays;
import java.util.List;

public class Utils {

    public static List<String> getInputList(String input) {
        return Arrays.asList(input.split(""));
    }

    public static List<String> getKeywordList(String input, String keyword) {
        StringBuilder keywordConcat = new StringBuilder();
        while (keywordConcat.length() < input.length()) {
            keywordConcat.append(keyword);
        }
        return Arrays.asList(keywordConcat
                .substring(0, input.length())
                .split(""));
    }

}
