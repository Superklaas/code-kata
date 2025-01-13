import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GuardPositioner {

//    public static final String INPUT = "advent-of-code-2024/day-6/input/test-input-2.txt";
    public static final String INPUT = "advent-of-code-2024/day-6/input/input.txt";

    public static final String DOT = ".";
    public static final String HASHTAG = "#";
    public static final String X = "X";

    private static int pointerX;
    private static int pointerY;
    private static Direction direction = Direction.UP;

    private static int counter = 0;

    public static void main(String[] args) throws IOException {

        String[][] grid = Files.readAllLines(Path.of(INPUT))
                .stream()
                .map(line -> line.split(""))
                .toArray(String[][]::new);

        OUTER:
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if ("^".equals(grid[y][x])) {
                    pointerX = x;
                    pointerY = y;
                    grid[y][x] = X;
                    counter++;
                    break OUTER;
                }
            }
        }

        while (!(0 == pointerX || grid[0].length - 1 == pointerX || 0 == pointerY || grid.length - 1 == pointerY)) {
            switch (direction) {
                case UP -> moveUp(grid);
                case RIGHT -> moveRight(grid);
                case DOWN -> moveDown(grid);
                case LEFT -> moveLeft(grid);
            }
        }
        printGrid(grid);
        System.out.println(counter);

    }

    private static void moveLeft(String[][] grid) {
        String nextPosition = grid[pointerY][pointerX - 1];
        switch (nextPosition) {
            case DOT -> {
                grid[pointerY][pointerX - 1] = X;
                counter++;
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
                counter++;
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
                counter++;
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
                counter++;
                pointerY = pointerY - 1;
            }
            case X -> pointerY = pointerY - 1;
            case HASHTAG -> direction = Direction.RIGHT;
        }
    }

    private static void printGrid(String[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                System.out.print(grid[y][x]);
            }
            System.out.println();
        }
    }

}
