import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuardPositioner {

    static final String INPUT = "advent-of-code-2024/day-6/input/input.txt";

    static final String DOT = ".";
    static final String HASHTAG = "#";
    static final String X = "X";

    static String[][] grid;
    static int numberCellsInGrid;
    static int pointerX, pointerY;
    static Direction direction;

    static int checkPointCounter, guardStuckCounter;

    public static void main(String[] args) throws IOException {

        initializeGrid();

        // PART 1

        while (!isAtBorder()) move();
        System.out.println(checkPointCounter);

        // PART 2

        initializeGrid();

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (DOT.equals(grid[y][x])) {
                    grid[y][x] = HASHTAG;
                    boolean isGuardStuck = checkIfGuardGetsStuck();
                    if (isGuardStuck) guardStuckCounter++;
                }
                initializeGrid();
            }
        }

        System.out.println(guardStuckCounter);

    }

    private static void initializeGrid() throws IOException {
        grid = getOriginalGrid();
        numberCellsInGrid = grid.length * grid[0].length;
        getInitialCoordinates(grid);
        checkPointCounter = 1;
        direction = Direction.UP;
    }

    private static String[][] getOriginalGrid() throws IOException {
        return Files.readAllLines(Path.of(INPUT))
                .stream()
                .map(line -> line.split(""))
                .toArray(String[][]::new);
    }

    private static void getInitialCoordinates(String[][] grid) {
        OUTER:
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if ("^".equals(grid[y][x])) {
                    pointerX = x;
                    pointerY = y;
                    grid[y][x] = X;
                    break OUTER;
                }
            }
        }
    }

    private static boolean isAtBorder() {
        return 0 == pointerX || grid[0].length - 1 == pointerX || 0 == pointerY || grid.length - 1 == pointerY;
    }

    private static boolean checkIfGuardGetsStuck() {
        for (int i = 0; i < numberCellsInGrid; i++) {
            move();
            if (isAtBorder()) return false;
        }
        return true;
    }

    private static void move() {
        switch (direction) {
            case UP -> moveUp(grid);
            case RIGHT -> moveRight(grid);
            case DOWN -> moveDown(grid);
            case LEFT -> moveLeft(grid);
        }
    }

    private static void moveLeft(String[][] grid) {
        String nextPosition = grid[pointerY][pointerX - 1];
        switch (nextPosition) {
            case DOT -> {
                grid[pointerY][pointerX - 1] = X;
                checkPointCounter++;
                pointerX = pointerX - 1;
            }
            case X -> pointerX = pointerX - 1;
            case HASHTAG -> direction = Direction.UP;
        }
    }

    private static void moveDown(String[][] grid) {
        String nextPosition = grid[pointerY + 1][pointerX];
        switch (nextPosition) {
            case DOT -> {
                grid[pointerY + 1][pointerX] = X;
                checkPointCounter++;
                pointerY = pointerY + 1;
            }
            case X -> pointerY = pointerY + 1;
            case HASHTAG -> direction = Direction.LEFT;
        }
    }

    private static void moveRight(String[][] grid) {
        String nextPosition = grid[pointerY][pointerX + 1];
        switch (nextPosition) {
            case DOT -> {
                grid[pointerY][pointerX + 1] = X;
                checkPointCounter++;
                pointerX = pointerX + 1;
            }
            case X -> pointerX = pointerX + 1;
            case HASHTAG -> direction = Direction.DOWN;
        }
    }

    private static void moveUp(String[][] grid) {
        String nextPosition = grid[pointerY - 1][pointerX];
        switch (nextPosition) {
            case DOT -> {
                grid[pointerY - 1][pointerX] = X;
                checkPointCounter++;
                pointerY = pointerY - 1;
            }
            case X -> pointerY = pointerY - 1;
            case HASHTAG -> direction = Direction.RIGHT;
        }
    }

    private static void printGrid(String[][] grid) {
        for (String[] row : grid) {
            System.out.println(String.join("", row));
        }
    }

}
