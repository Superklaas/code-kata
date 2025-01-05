import java.io.IOException;

public class Part1 {

    public static void main(String[] args) throws IOException {

        String[][] inputArray = Commons.convertInputTo2DArray();

        int countX = 0;
        for (int x = 0; x < inputArray.length; x++) {
            for (int y = 0; y < inputArray[x].length; y++) {
                if ("X".equals(inputArray[x][y])) {
                    countX = checkThreeOClock(inputArray, countX, x, y);
                    countX = checkNineOClock(inputArray, countX, x, y);
                    countX = checkTwelveOClock(inputArray, countX, x, y);
                    countX = checkSixOClock(inputArray, countX, x, y);
                    countX = checkTenThirty(inputArray, countX, x, y);
                    countX = checkOneThirty(inputArray, countX, x, y);
                    countX = checkFourThirty(inputArray, countX, x, y);
                    countX = checkSevenThirty(inputArray, countX, x, y);
                }
            }
        }

        System.out.println(countX);

    }

    private static int checkSevenThirty(String[][] inputArray, int count, int x, int y) {
        if (x - 3 > -1 && y + 3 < inputArray[x].length &&
                "M".equals(inputArray[x - 1][y + 1]) &&
                "A".equals(inputArray[x - 2][y + 2]) &&
                "S".equals(inputArray[x - 3][y + 3])) {
            count++;
        }
        return count;
    }

    private static int checkFourThirty(String[][] inputArray, int count, int x, int y) {
        if (x + 3 < inputArray.length && y + 3 < inputArray[x].length &&
                "M".equals(inputArray[x + 1][y + 1]) &&
                "A".equals(inputArray[x + 2][y + 2]) &&
                "S".equals(inputArray[x + 3][y + 3])) {
            count++;
        }
        return count;
    }

    private static int checkOneThirty(String[][] inputArray, int count, int x, int y) {
        if (x + 3 < inputArray.length && y - 3 > -1 &&
                "M".equals(inputArray[x + 1][y - 1]) &&
                "A".equals(inputArray[x + 2][y - 2]) &&
                "S".equals(inputArray[x + 3][y - 3])) {
            count++;
        }
        return count;
    }

    private static int checkTenThirty(String[][] inputArray, int count, int x, int y) {
        if (x - 3 > -1 && y - 3 > -1 &&
                "M".equals(inputArray[x - 1][y - 1]) &&
                "A".equals(inputArray[x - 2][y - 2]) &&
                "S".equals(inputArray[x - 3][y - 3])) {
            count++;
        }
        return count;
    }

    private static int checkSixOClock(String[][] inputArray, int count, int x, int y) {
        if (x + 3 < inputArray.length &&
                "M".equals(inputArray[x + 1][y]) &&
                "A".equals(inputArray[x + 2][y]) &&
                "S".equals(inputArray[x + 3][y])) {
            count++;
        }
        return count;
    }

    private static int checkTwelveOClock(String[][] inputArray, int count, int x, int y) {
        if (x - 3 > -1 &&
                "M".equals(inputArray[x - 1][y]) &&
                "A".equals(inputArray[x - 2][y]) &&
                "S".equals(inputArray[x - 3][y])) {
            count++;
        }
        return count;
    }

    private static int checkNineOClock(String[][] inputArray, int count, int x, int y) {
        if (y - 3 > -1 &&
                "M".equals(inputArray[x][y - 1]) &&
                "A".equals(inputArray[x][y - 2]) &&
                "S".equals(inputArray[x][y - 3])) {
            count++;
        }
        return count;
    }

    private static int checkThreeOClock(String[][] inputArray, int count, int x, int y) {
        if (y + 3 < inputArray[x].length &&
                "M".equals(inputArray[x][y + 1]) &&
                "A".equals(inputArray[x][y + 2]) &&
                "S".equals(inputArray[x][y + 3])) {
            count++;
        }
        return count;
    }

}
