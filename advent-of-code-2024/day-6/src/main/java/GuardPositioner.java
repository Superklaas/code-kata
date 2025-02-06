import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuardPositioner {

    static final String INPUT = "advent-of-code-2024/day-6/input/input.txt";
    static final String DOT = ".";
    static final String HASHTAG = "#";
    static final String X = "X";

    String[][] grid, originalGrid;
    int numberCellsInGrid;
    int pointerX, pointerY, originalPointerX, originalPointerY;
    Direction direction;
    int checkPointCounter, guardStuckCounter;

    public static void main(String[] args) throws IOException {
        GuardPositioner guardPositioner = new GuardPositioner();
        guardPositioner.initializeGrid();
        guardPositioner.moveToBorder();
        guardPositioner.addObstruction();
    }

    void initializeGrid() throws IOException {
        originalGrid = getOriginalGrid();
        numberCellsInGrid = originalGrid.length * originalGrid[0].length;
        getOriginalPositionGuard(originalGrid);
        resetGrid();
    }

    void moveToBorder() {
        while (!isAtBorder()) move(grid);
        System.out.println(checkPointCounter);
    }

    void addObstruction() {
        resetGrid();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (DOT.equals(grid[y][x])) {
                    grid[y][x] = HASHTAG;
                    boolean isGuardStuck = checkIfGuardGetsStuck();
                    if (isGuardStuck) guardStuckCounter++;
                }
                resetGrid();
            }
        }
        System.out.println(guardStuckCounter);
    }

    private String[][] getOriginalGrid() throws IOException {
        return Files.readAllLines(Path.of(INPUT))
                .stream()
                .map(line -> line.split(""))
                .toArray(String[][]::new);
    }

    private void getOriginalPositionGuard(String[][] grid) {
        OUTER:
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if ("^".equals(grid[y][x])) {
                    originalPointerX = x;
                    originalPointerY = y;
                    grid[y][x] = X;
                    break OUTER;
                }
            }
        }
    }

    private void resetGrid() {
        grid = new String[originalGrid.length][originalGrid[0].length];
        for (int i = 0; i < originalGrid.length; i++) {
            System.arraycopy(originalGrid[i], 0, grid[i], 0, originalGrid[i].length);
        }
        pointerX = originalPointerX;
        pointerY = originalPointerY;
        direction = Direction.UP;
        checkPointCounter = 1;
    }

    private boolean isAtBorder() {
        return 0 == pointerX || grid[0].length - 1 == pointerX || 0 == pointerY || grid.length - 1 == pointerY;
    }

    private boolean checkIfGuardGetsStuck() {
        for (int i = 0; i < numberCellsInGrid; i++) {
            move(grid);
            if (isAtBorder()) return false;
        }
        return true;
    }

    private void move(String[][] grid) {
        int nextPositionY = pointerY + direction.getDy();
        int nextPositionX = pointerX + direction.getDx();
        String nextPosition = grid[nextPositionY][nextPositionX];
        switch (nextPosition) {
            case DOT -> {
                grid[nextPositionY][nextPositionX] = X;
                checkPointCounter++;
                pointerX = nextPositionX;
                pointerY = nextPositionY;
            }
            case X -> {
                pointerX = nextPositionX;
                pointerY = nextPositionY;
            }
            case HASHTAG -> direction = Direction.setNewDirection(direction);
        }
    }

    private void printGrid(String[][] grid) {
        for (String[] row : grid) {
            System.out.println(String.join("", row));
        }
    }

}
