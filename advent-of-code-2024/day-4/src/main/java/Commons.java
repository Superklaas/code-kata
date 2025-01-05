import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class Commons {

    private static final String INPUT = "advent-of-code-2024/day-4/input/input.txt";

    public static String[][] convertInputTo2DArray() throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(INPUT))) {
            return lines
                    .map(s -> s.split(""))
                    .toArray(String[][]::new);
        }
    }

}
