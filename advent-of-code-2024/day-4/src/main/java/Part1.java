import java.io.IOException;

public class Part1 {

    public static void main(String[] args) throws IOException {

        String[][] inputArray = Commons.convertInputTo2DArray();

        int countX = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                if ("X".equals(inputArray[i][j])) {
                    countX = checkThreeOClock(inputArray, countX, i, j);
                    countX = checkNineOClock(inputArray, countX, i, j);
                    countX = checkTwelveOClock(inputArray, countX, i, j);
                    countX = checkSixOClock(inputArray, countX, i, j);
                    countX = checkTenThirty(inputArray, countX, i, j);
                    countX = checkOneThirty(inputArray, countX, i, j);
                    countX = checkFourThirty(inputArray, countX, i, j);
                    countX = checkSevenThirty(inputArray, countX, i, j);
                }
            }
        }

        System.out.println(countX);

    }

    private static int checkSevenThirty(String[][] inputArray, int count, int i, int j) {
        if (i - 3 > -1 && j + 3 < inputArray[i].length &&
                "M".equals(inputArray[i - 1][j + 1]) &&
                "A".equals(inputArray[i - 2][j + 2]) &&
                "S".equals(inputArray[i - 3][j + 3])) {
            count++;
        }
        return count;
    }

    private static int checkFourThirty(String[][] inputArray, int count, int i, int j) {
        if (i + 3 < inputArray.length && j + 3 < inputArray[i].length &&
                "M".equals(inputArray[i + 1][j + 1]) &&
                "A".equals(inputArray[i + 2][j + 2]) &&
                "S".equals(inputArray[i + 3][j + 3])) {
            count++;
        }
        return count;
    }

    private static int checkOneThirty(String[][] inputArray, int count, int i, int j) {
        if (i + 3 < inputArray.length && j - 3 > -1 &&
                "M".equals(inputArray[i + 1][j - 1]) &&
                "A".equals(inputArray[i + 2][j - 2]) &&
                "S".equals(inputArray[i + 3][j - 3])) {
            count++;
        }
        return count;
    }

    private static int checkTenThirty(String[][] inputArray, int count, int i, int j) {
        if (i - 3 > -1 && j - 3 > -1 &&
                "M".equals(inputArray[i - 1][j - 1]) &&
                "A".equals(inputArray[i - 2][j - 2]) &&
                "S".equals(inputArray[i - 3][j - 3])) {
            count++;
        }
        return count;
    }

    private static int checkSixOClock(String[][] inputArray, int count, int i, int j) {
        if (i + 3 < inputArray.length &&
                "M".equals(inputArray[i + 1][j]) &&
                "A".equals(inputArray[i + 2][j]) &&
                "S".equals(inputArray[i + 3][j])) {
            count++;
        }
        return count;
    }

    private static int checkTwelveOClock(String[][] inputArray, int count, int i, int j) {
        if (i - 3 > -1 &&
                "M".equals(inputArray[i - 1][j]) &&
                "A".equals(inputArray[i - 2][j]) &&
                "S".equals(inputArray[i - 3][j])) {
            count++;
        }
        return count;
    }

    private static int checkNineOClock(String[][] inputArray, int count, int i, int j) {
        if (j - 3 > -1 &&
                "M".equals(inputArray[i][j - 1]) &&
                "A".equals(inputArray[i][j - 2]) &&
                "S".equals(inputArray[i][j - 3])) {
            count++;
        }
        return count;
    }

    private static int checkThreeOClock(String[][] inputArray, int count, int i, int j) {
        if (j + 3 < inputArray[i].length &&
                "M".equals(inputArray[i][j + 1]) &&
                "A".equals(inputArray[i][j + 2]) &&
                "S".equals(inputArray[i][j + 3])) {
            count++;
        }
        return count;
    }

}
