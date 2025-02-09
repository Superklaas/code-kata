import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day1 {

    static final Path INPUT = Path.of("advent-of-code-2024/day-1/input/location-ids.txt");

    public static void main(String[] args) throws IOException {

        List<Integer> numbersLeft;
        try (Stream<String> lines = Files.lines(INPUT)) {
            numbersLeft = lines
                    .map(line -> line.substring(0, 5))
                    .map(Integer::valueOf)
                    .sorted()
                    .toList();
        }

        List<Integer> numbersRight;
        try (Stream<String> lines = Files.lines(INPUT)) {
            numbersRight = lines
                    .map(line -> line.substring(8))
                    .map(Integer::valueOf)
                    .sorted()
                    .toList();
        }

        // PART 1: sum distances
        int sumDistances = IntStream.range(0, numbersLeft.size())
                .map(i -> Math.abs(numbersRight.get(i) - numbersLeft.get(i)))
                .sum();
        System.out.println(sumDistances);

        // PART 2: similarity score
        // formula per similarity: number * timesLeft * timesRight
        List<Integer> distinctNumbersLeft = numbersLeft.stream().distinct().toList();
        long similarityScore = 0;
        for (Integer number : distinctNumbersLeft) {
            long timesLeft = numbersLeft.stream()
                    .filter(i -> Objects.equals(i, number))
                    .count();
            long timesRight = numbersRight.stream()
                    .filter(i -> Objects.equals(i, number))
                    .count();
            similarityScore += number * timesLeft * timesRight;
        }
        System.out.println(similarityScore);

    }

}
