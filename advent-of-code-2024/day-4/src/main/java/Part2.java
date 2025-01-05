import java.io.IOException;

public class Part2 {

    public static void main(String[] args) throws IOException {

        String[][] inputArray = Commons.convertInputTo2DArray();

        int countMas = 0;
        for (int x = 0; x < inputArray.length; x++) {
            for (int y = 0; y < inputArray[x].length; y++) {
                if ("M".equals(inputArray[x][y])) {
                    countMas = checkMasMas(inputArray, countMas, x, y);
                    countMas = checkMasSam(inputArray, countMas, x, y);
                }
                if ("S".equals(inputArray[x][y])) {
                    countMas = checkSamMas(inputArray, countMas, x, y);
                    countMas = checkSamSam(inputArray, countMas, x, y);
                }
            }
        }
        System.out.println(countMas);

    }

    private static int checkSamSam(String[][] inputArray, int count, int x, int y) {
        if (x + 2 < inputArray.length && y + 2 < inputArray[x].length &&
                "S".equals(inputArray[x + 2][y]) &&
                "A".equals(inputArray[x + 1][y + 1]) &&
                "M".equals(inputArray[x][y + 2]) &&
                "M".equals(inputArray[x + 2][y + 2])) {
            count++;
        }
        return count;
    }

    private static int checkSamMas(String[][] inputArray, int count, int x, int y) {
        if (x + 2 < inputArray.length && y + 2 < inputArray[x].length &&
                "M".equals(inputArray[x + 2][y]) &&
                "A".equals(inputArray[x + 1][y + 1]) &&
                "S".equals(inputArray[x][y + 2]) &&
                "M".equals(inputArray[x + 2][y + 2])) {
            count++;
        }
        return count;
    }

    private static int checkMasSam(String[][] inputArray, int count, int x, int y) {
        if (x + 2 < inputArray.length && y + 2 < inputArray[x].length &&
                "S".equals(inputArray[x + 2][y]) &&
                "A".equals(inputArray[x + 1][y + 1]) &&
                "M".equals(inputArray[x][y + 2]) &&
                "S".equals(inputArray[x + 2][y + 2])) {
            count++;
        }
        return count;
    }

    private static int checkMasMas(String[][] inputArray, int count, int x, int y) {
        if (x + 2 < inputArray.length && y + 2 < inputArray[x].length &&
                "M".equals(inputArray[x + 2][y]) &&
                "A".equals(inputArray[x + 1][y + 1]) &&
                "S".equals(inputArray[x][y + 2]) &&
                "S".equals(inputArray[x + 2][y + 2])) {
            count++;
        }
        return count;
    }

}
