import java.io.IOException;

public class Part2 {

    public static void main(String[] args) throws IOException {

        String[][] inputArray = Commons.convertInputTo2DArray();

        int countMas = 0;
        for (int i = 0; i < inputArray.length; i++) {
            for (int j = 0; j < inputArray[i].length; j++) {
                if ("M".equals(inputArray[i][j])) {
                    countMas = checkMasMas(inputArray, countMas, i, j);
                    countMas = checkMasSam(inputArray, countMas, i, j);
                }
                if ("S".equals(inputArray[i][j])) {
                    countMas = checkSamMas(inputArray, countMas, i, j);
                    countMas = checkSamSam(inputArray, countMas, i, j);
                }
            }
        }
        System.out.println(countMas);

    }

    private static int checkSamSam(String[][] inputArray, int count, int i, int j) {
        if (i + 2 < inputArray.length && j + 2 < inputArray[i].length &&
                "S".equals(inputArray[i + 2][j]) &&
                "A".equals(inputArray[i + 1][j + 1]) &&
                "M".equals(inputArray[i][j + 2]) &&
                "M".equals(inputArray[i + 2][j + 2])) {
            count++;
        }
        return count;
    }

    private static int checkSamMas(String[][] inputArray, int count, int i, int j) {
        if (i + 2 < inputArray.length && j + 2 < inputArray[i].length &&
                "M".equals(inputArray[i + 2][j]) &&
                "A".equals(inputArray[i + 1][j + 1]) &&
                "S".equals(inputArray[i][j + 2]) &&
                "M".equals(inputArray[i + 2][j + 2])) {
            count++;
        }
        return count;
    }

    private static int checkMasSam(String[][] inputArray, int count, int i, int j) {
        if (i + 2 < inputArray.length && j + 2 < inputArray[i].length &&
                "S".equals(inputArray[i + 2][j]) &&
                "A".equals(inputArray[i + 1][j + 1]) &&
                "M".equals(inputArray[i][j + 2]) &&
                "S".equals(inputArray[i + 2][j + 2])) {
            count++;
        }
        return count;
    }

    private static int checkMasMas(String[][] inputArray, int count, int i, int j) {
        if (i + 2 < inputArray.length && j + 2 < inputArray[i].length &&
                "M".equals(inputArray[i + 2][j]) &&
                "A".equals(inputArray[i + 1][j + 1]) &&
                "S".equals(inputArray[i][j + 2]) &&
                "S".equals(inputArray[i + 2][j + 2])) {
            count++;
        }
        return count;
    }

}
