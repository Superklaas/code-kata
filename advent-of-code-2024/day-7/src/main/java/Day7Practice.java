import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

class Day7Practice {

    public static final int START_INDEX = 0;

    public static void main(String[] args) {
        List<Integer> operands = IntStream.rangeClosed(1, 5).boxed().toList();
        List<Integer> results = new LinkedList<>();
        computeResults(operands, results, operands.get(START_INDEX), START_INDEX + 1);
        System.out.println(results);
        System.out.println(results.size());
    }

    private static void computeResults(List<Integer> operands, List<Integer> results, Integer currentValue, int nextIndex) {
        if (nextIndex == operands.size()) {
            results.add(currentValue);
            return;
        }
        int nextValue = operands.get(nextIndex);
        computeResults(operands, results, currentValue + nextValue, nextIndex + 1);
        computeResults(operands, results, currentValue * nextValue, nextIndex + 1);
        computeResults(operands, results, currentValue - nextValue, nextIndex + 1);
    }

}
