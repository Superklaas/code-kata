import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Grid {

    public static final String DOT = ".";
    public static final String HASHTAG = "#";
    public static final String X = "X";

    private String[][] currentGrid;
    private final String[][] originalGrid;
    private final int numberCellsInGrid;

    public Grid(String input) throws IOException {
        originalGrid = getOriginalGridFromInput(input);
        numberCellsInGrid = originalGrid.length * originalGrid[0].length;
        resetGrid();
    }

    private String[][] getOriginalGridFromInput(String input) throws IOException {
        return Files.readAllLines(Path.of(input))
                .stream()
                .map(line -> line.split(""))
                .toArray(String[][]::new);
    }

    public void resetGrid() {
        currentGrid = new String[originalGrid.length][originalGrid[0].length];
        for (int i = 0; i < originalGrid.length; i++) {
            System.arraycopy(originalGrid[i], 0, currentGrid[i], 0, originalGrid[i].length);
        }
    }

    public void printCurrentGrid() {
        for (String[] row : currentGrid) {
            System.out.println(String.join("", row));
        }
        System.out.println("------------------------");
    }

    public String[][] getCurrentGrid() {
        return currentGrid;
    }

    public String[][] getOriginalGrid() {
        return originalGrid;
    }

    public int getNumberCellsInGrid() {
        return numberCellsInGrid;
    }

}
