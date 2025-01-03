import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static final String COORDINATES_FILE = "advent-of-code-2021-day-13/input/coordinates2.txt";
    public static final String FOLD_INSTRUCTIONS_FILE = "advent-of-code-2021-day-13/input/foldInstructions2.txt";

    public static void main(String[] args) throws IOException {

        List<Coordinate> coordinates = getCoordinatesFromFile();
        List<FoldInstruction> foldInstructions = getFoldInstructionsFromFile();

//        printGrid(coordinates); // before folding

        for (FoldInstruction foldInstruction : foldInstructions) {
            coordinates = fold(coordinates, foldInstruction);
        }

        printGrid(coordinates); // after folding

    }

    private static List<Coordinate> getCoordinatesFromFile() throws IOException {
        try (Stream<String> coordinatesStream = Files.lines(Path.of(COORDINATES_FILE))) {
            return coordinatesStream
                    .map(string -> string.split(","))
                    .map(stringArray -> new Coordinate(
                            Integer.parseInt(stringArray[0]),
                            Integer.parseInt(stringArray[1])
                    ))
                    .toList();
        }
    }

    private static List<FoldInstruction> getFoldInstructionsFromFile() throws IOException {
        Path foldInstructionsFile = Path.of(FOLD_INSTRUCTIONS_FILE);
        return Files.readAllLines(foldInstructionsFile).stream()
                .map(s -> s.substring(11))
                .map(s -> new FoldInstruction(
                        FoldInstruction.FoldAxis.valueOf(s.substring(0, 1).toUpperCase()),
                        Integer.parseInt(s.substring(2))
                ))
                .toList();
    }

    private static List<Coordinate> fold(List<Coordinate> coordinates, FoldInstruction foldInstruction) {
        String foldAxis = foldInstruction.foldAxis().name();
        int foldLine = foldInstruction.foldLine();
        List<Coordinate> newCoordinates = new ArrayList<>();
        if ("Y".equals(foldAxis)) {
            for (Coordinate coordinate : coordinates) {
                if (coordinate.y() > foldLine) {
                    newCoordinates.add(new Coordinate(coordinate.x(), 2 * foldLine - coordinate.y()));
                } else {
                    newCoordinates.add(coordinate);
                }
            }
        } else {
            for (Coordinate coordinate : coordinates) {
                if (coordinate.x() > foldLine) {
                    newCoordinates.add(new Coordinate(2 * foldLine - coordinate.x(), coordinate.y()));
                } else {
                    newCoordinates.add(coordinate);
                }
            }
        }
        return newCoordinates;
    }

    private static void printGrid(List<Coordinate> coordinates) {
        int maxX = getMaxX(coordinates);
        int maxY = getMaxY(coordinates);
        String[][] grid = new String[maxX][maxY];
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                grid[x][y] = coordinates.contains(new Coordinate(x, y)) ? "&" : ".";
                System.out.print(grid[x][y]);
            }
            System.out.println();
        }
    }

    private static int getMaxX(List<Coordinate> coordinates) {
        return coordinates.stream()
                .map(Coordinate::x)
                .map(integer -> integer + 1)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private static int getMaxY(List<Coordinate> coordinates) {
        return coordinates.stream()
                .map(Coordinate::y)
                .map(integer -> integer + 1)
                .max(Integer::compareTo)
                .orElse(0);
    }

}
