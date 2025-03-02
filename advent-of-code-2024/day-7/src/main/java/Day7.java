import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day7 {

    public static final String INPUT = "advent-of-code-2024/day-7/input/input.txt";
    public static final String TEST_INPUT = "advent-of-code-2024/day-7/input/test-input.txt";

    private List<Long> allResults;
    private List<List<Long>> allOperands;

    public static void main(String[] args) throws IOException {
        Day7 day7 = new Day7();
        day7.parseInput();
        // PART 1: addition & multiplication
        long sumValidResults = day7.calculateSumValidResults(false);
        System.out.println(sumValidResults);
        // PART 2: cf. part 1 + concatenation
        sumValidResults = day7.calculateSumValidResults(true);
        System.out.println(sumValidResults);
    }

    private void parseInput() throws IOException {
        List<String[]> resultsWithOperands = Files.readAllLines(Path.of(TEST_INPUT)).stream()
                .map(inputLine -> inputLine.split(":", 2))
                .filter(array -> array.length == 2)
                .toList();
        allResults = resultsWithOperands.stream()
                .map(array -> array[0].trim())
                .map(Long::parseLong)
                .toList();
        allOperands = resultsWithOperands.stream()
                .map(array -> Arrays.stream(array[1].trim().split(" "))
                        .map(Long::parseLong)
                        .toList())
                .toList();
    }

    private long calculateSumValidResults(boolean withConcat) {
        long sumValidResults = 0L;
        for (int i = 0; i < allResults.size(); i++) {
            Long actualResult = allResults.get(i);
            List<Long> operands = allOperands.get(i);
            Set<Long> possibleResults = computePossibleResults(operands, withConcat);
            if (possibleResults.contains(actualResult)) sumValidResults += actualResult;
        }
        return sumValidResults;
    }

    public static Set<Long> computePossibleResults(List<Long> operands, boolean withConcat) {
        Set<Long> possibleResults = new HashSet<>();
        if (operands == null || operands.isEmpty()) return possibleResults;
        compute(operands, 1, operands.get(0), possibleResults, withConcat);
        return possibleResults;
    }

    private static void compute(List<Long> operands, int nextIndex, long currentValue, Set<Long> possibleResults, boolean withConcat) {
        if (nextIndex == operands.size()) {
            possibleResults.add(currentValue);
            return;
        }
        long nextValue = operands.get(nextIndex);
        compute(operands, nextIndex + 1, currentValue + nextValue, possibleResults, withConcat);
        compute(operands, nextIndex + 1, currentValue * nextValue, possibleResults, withConcat);
        if (withConcat) {
            compute(operands, nextIndex + 1, Long.parseLong(currentValue + "" + nextValue), possibleResults, true);
        }
    }

}
