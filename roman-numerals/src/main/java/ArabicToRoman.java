import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArabicToRoman {

    public static void main(String[] args) {

        String input = "1109";
        StringBuilder output = new StringBuilder();

        List<Integer> inputList = convertInputToList(input);

        validateInput(inputList);

        while (inputList.size() != 0) {
            calculateRomanNumeral(output, inputList);
        }

        System.out.println("arabic number " + input + " converted to roman number " + output);
    }

    private static void calculateRomanNumeral(StringBuilder output, List<Integer> inputList) {
        int rank = inputList.size() - 1;
        RomanNumeral romanNumeral1 = RomanNumeral.values()[2 * rank];
        RomanNumeral romanNumeral2 = RomanNumeral.values()[2 * rank + 1];
        RomanNumeral romanNumeral3 = RomanNumeral.values()[2 * rank + 2];
        Integer consideredNumber = inputList.get(0);
        if (consideredNumber / 9 == 1) {
            output.append(romanNumeral1.getRomanValue());
            output.append(romanNumeral3.getRomanValue());
        } else if (consideredNumber / 5 == 1) {
            output.append(romanNumeral2.getRomanValue());
            output.append(romanNumeral1.getRomanValue().repeat(Math.max(0, consideredNumber - 5)));
        } else if (consideredNumber / 4 == 1) {
            output.append(romanNumeral1.getRomanValue()).append(romanNumeral2.getRomanValue());
        } else {
            output.append(romanNumeral1.getRomanValue().repeat(Math.max(0, consideredNumber)));
        }
        inputList.remove(inputList.get(0));
    }


    private static void validateInput(List<Integer> inputList) {
        if (inputList.size() > 4) {
            throw new InvalidNumberException("input can contain maximum 4 digits");
        }
        if (inputList.size() > 3 && inputList.get(0) > 3) {
            throw new InvalidNumberException("input has to be smaller than 4000");
        }
        if (inputList.get(0) == 0) {
            throw new InvalidNumberException("first digit input cannot be zero");
        }
    }

    private static List<Integer> convertInputToList(String input) {
        List<Integer> inputList;
        try {
            inputList = new ArrayList<>(Arrays.stream(input.split(""))
                    .map(Integer::parseInt)
                    .toList());
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("input string can only contain numerals");
        }
        return inputList;
    }

}
