import java.io.IOException;

public class Part2 {

    // four possible crosses: MasMas, MasSam, SamMas, SamSam
    private static final String[][] letterCombinations = {
            {"M", "M", "S", "S"},
            {"M", "S", "M", "S"},
            {"S", "M", "S", "M"},
            {"S", "S", "M", "M"},
    };
    private static int counter;

    public static void main(String[] args) throws IOException {
        String[][] inputArray = Commons.convertInputTo2DArray();
        for (int x = 0; x < inputArray.length; x++) {
            for (int y = 0; y < inputArray[x].length; y++) {
                for (String[] letterCombination : letterCombinations) {
                    counter += checkMas(inputArray, x, y, letterCombination) ? 1 : 0;
                }
            }
        }
        System.out.println(counter);

    }

    private static boolean checkMas(String[][] inputArray, int x, int y, String[] letterCombination) {
        return x + 2 < inputArray.length &&
                y + 2 < inputArray[x].length &&
                "A".equals(inputArray[x + 1][y + 1]) &&
                letterCombination[0].equals(inputArray[x][y]) &&
                letterCombination[1].equals(inputArray[x][y + 2]) &&
                letterCombination[2].equals(inputArray[x + 2][y]) &&
                letterCombination[3].equals(inputArray[x + 2][y + 2]);
    }

}
