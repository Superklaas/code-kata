import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Commons {

    static final String LOCATION_ID_FILE = "advent-of-code-2024/day-1/input/location-ids.txt";

    static List<Integer> getNumbersLeft() throws IOException {
        return Files.readAllLines(Path.of(LOCATION_ID_FILE)).stream()
                .map(s -> s.substring(0, 5))
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }

    static List<Integer> getNumbersRight() throws IOException {
        return Files.readAllLines(Path.of(LOCATION_ID_FILE)).stream()
                .map(s -> s.substring(8))
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }

}
