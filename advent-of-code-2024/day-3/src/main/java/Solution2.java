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

        /* PART ONE */
        ArrayList<String> mulExpressions = getMulExpressions(input);
        long sumPart1 = getSumOfProducts(mulExpressions);
        System.out.println(sumPart1);

        /* PART TWO */

        long sumPart2 = 0;

        String[] splitsByDont = input.split("don't\\(\\)");

        // get products from part before first disabling
        String firstPart = splitsByDont[0];
        ArrayList<String> mulExpressions1 = getMulExpressions(firstPart);
        long sumFirstSplit = getSumOfProducts(mulExpressions1);
        sumPart2 += sumFirstSplit;

        // get sum from enabled parts
        for (int i = 1; i < splitsByDont.length; i++) {
            String[] splitsByDo = splitsByDont[i].split("do",2);
            if (splitsByDo.length > 1) {
                for (int j = 1; j < splitsByDo.length; j++) {
                    ArrayList<String> mulExpressions2 = getMulExpressions(splitsByDo[j]);
                    long sumEnabledSplit = getSumOfProducts(mulExpressions2);
                    sumPart2 += sumEnabledSplit;
                }
            }
        }

        System.out.println(sumPart2);

    }

    private static long getSumOfProducts(ArrayList<String> mulExpressions) {
        long sum = 0;
        for (String mulExpression : mulExpressions) {
            String[] factors = mulExpression.substring(4, mulExpression.indexOf(")")).split(",");
            long product = Arrays.stream(factors)
                    .map(Integer::parseInt)
                    .reduce((a, b) -> a * b)
                    .orElse(0);
            sum += product;
        }
        return sum;
    }

    private static ArrayList<String> getMulExpressions(String input) {
        String patternString = "mul\\([0-9]+,[0-9]+\\)";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(input);
        ArrayList<String> mulExpressions = new ArrayList<>();
        while (matcher.find()) {
            mulExpressions.add(matcher.group());
        }
        return mulExpressions;
    }

}
