import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution2 {

    public static final String INPUT = "advent-of-code-2024/day-3/input/input.txt";

    public static void main(String[] args) throws IOException {

        String input = Files.readString(Path.of(INPUT));
        String patternString = "mul\\([0-9]+,[0-9]+\\)";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> mulExpressions = new ArrayList<>();
        while (matcher.find()) {
            mulExpressions.add(matcher.group());
        }
        System.out.println(mulExpressions);

        long sum = 0;
        for (String mulExpression : mulExpressions) {
            String[] factors = mulExpression.substring(4, mulExpression.indexOf(")")).split(",");
            long product = Arrays.stream(factors)
                    .map(Integer::parseInt)
                    .reduce((a, b) -> a * b)
                    .orElse(0);
            sum += product;
        }
        System.out.println(sum);

    }

}
