import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Solution1 {

    public static final String INPUT = "advent-of-code-2024/day-3/input/input.txt";

    public static void main(String[] args) throws IOException {

        String input = Files.readString(Path.of(INPUT));

        // split input in strings before and after keyword mul
        String[] mulExpressions = input.split("mul");

        // regex to find all strings starting with (digits,digits)
        // use dotall mode (?s) to make the dot expression . matches any character, including a line terminator.
        String regex = "^\\([0-9]+,[0-9]+\\)(?s).*$";

        // multiply factors per mulExpression & make sum of all products
        int sum = Arrays.stream(mulExpressions)
                .filter(s -> s.matches(regex))
                .map(s -> s.substring(1, s.indexOf(")")).split(","))
                .map(factors -> Arrays.stream(factors)
                        .map(Integer::parseInt)
                        .reduce((a, b) -> a * b)
                        .orElse(0))
                .mapToInt(i -> i)
                .sum();
        System.out.println(sum);

    }

}
