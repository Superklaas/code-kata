import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RomanToArabic {

    public static void main(String[] args) {

        String input = "XVIII";
        int output = 0;

        List<String> inputList = convertInputToList(input);

        validateInput(inputList);

        while (inputList.size() != 0) {
            output = calculateArabicNumeral(output, inputList);
        }

        System.out.println("roman number " + input + " converted to arabic number " + output);

    }

    private static int calculateArabicNumeral(int output, List<String> inputList) {
        int rank = RomanNumeral.valueOf(inputList.get(0)).getRank();
        RomanNumeral romanNumeral1 = RomanNumeral.valueOfIndex(2 * rank);
        RomanNumeral romanNumeral2 = RomanNumeral.valueOfIndex(2 * rank + 1);
        RomanNumeral romanNumeral3 = RomanNumeral.valueOfIndex(2 * rank + 2);
        if (romanNumeral2.getRomanValue().equals(inputList.get(0))) {
            output += romanNumeral2.getArabicValue();
            inputList.remove(inputList.get(0));
        }
        if (romanNumeral1.getRomanValue().equals(inputList.get(0))) {
            if (romanNumeral3.getRomanValue().equals(inputList.get(1))) {
                output += romanNumeral3.getArabicValue() - romanNumeral1.getArabicValue();
                inputList.remove(inputList.get(0));
                inputList.remove(inputList.get(0));
            } else if (romanNumeral2.getRomanValue().equals(inputList.get(1))) {
                output += romanNumeral2.getArabicValue() - romanNumeral1.getArabicValue();
                inputList.remove(inputList.get(0));
                inputList.remove(inputList.get(0));
            } else {
                while (inputList.size() > 0 && romanNumeral1.getRomanValue().equals(inputList.get(0))) {
                    output += romanNumeral1.getArabicValue();
                    inputList.remove(inputList.get(0));
                }
            }
        }
        return output;
    }

    private static void validateInput(List<String> inputList) {
        if (inputList.stream()
                .anyMatch(input -> !Arrays.stream(RomanNumeral.values())
                        .map(RomanNumeral::getRomanValue)
                        .toList()
                        .contains(input))) {
            throw new InvalidNumberException("input contains strings that are no roman numerals");
        }
    }

    private static List<String> convertInputToList(String input) {
        return new ArrayList<>(Arrays.stream(input.split("")).toList());
    }

}
