import java.io.IOException;

public class Part1 {

    static final int[][] directions = {
            {-1, 0},    // north
            {-1, 1},    // northeast
            {0, 1},     // east
            {1, 1},     // southeast
            {1, 0},     // south
            {1, -1},    // southwest
            {0, -1},    // west
            {-1, -1}    // northwest
    };

    private static int counter;

    public static void main(String[] args) throws IOException {

        String[][] inputArray = Commons.convertInputTo2DArray();

        for (int x = 0; x < inputArray.length; x++) {
            for (int y = 0; y < inputArray[x].length; y++) {
                if ("X".equals(inputArray[x][y])) {
                    for (int[] direction : directions) {
                        counter += checkDirection(inputArray, x, y, direction) ? 1 : 0;
                    }
                }
            }
        }

        System.out.println(counter);

    }

    private static boolean checkDirection(String[][] inputArray, int x, int y, int[] direction) {
        int dx = direction[0];
        int dy = direction[1];
        if (isOutsideGrid(inputArray.length, x, y, dx, dy)) return false;
        return isMatchingCharacters(inputArray, x, y, dx, dy);
    }

    private static boolean isOutsideGrid(int arrayLength, int x, int y, int dx, int dy) {
        return (dx == 1 && x + 3 >= arrayLength) ||
                (dx == -1 && x - 3 < 0) ||
                (dy == 1 && y + 3 >= arrayLength) ||
                (dy == -1 && y - 3 < 0);
    }

    private static boolean isMatchingCharacters(String[][] inputArray, int x, int y, int dx, int dy) {
        return "M".equals(inputArray[x + dx][y + dy]) &&
                "A".equals(inputArray[x + 2 * dx][y + 2 * dy]) &&
                "S".equals(inputArray[x + 3 * dx][y + 3 * dy]);
    }

}
