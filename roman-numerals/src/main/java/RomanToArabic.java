import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RomanToArabic {

    public static void main(String[] args) {

        String input = "MDCCXLIX";
        List<String> inputList = convertInputToList(input);
        validateInput(inputList);

        int output = 0;
        while (inputList.size() != 0) {
            output = calculateArabicNumeral(output, inputList);
        }

        System.out.println("roman number " + input + " converted to arabic number " + output);

    }

    private static int calculateArabicNumeral(int output, List<String> inputList) {
        int placeValue = RomanNumeral.valueOf(inputList.get(0)).getPlaceValue();
        RomanNumeral romanNumeral1 = RomanNumeral.values()[2 * placeValue];
        RomanNumeral romanNumeral2 = RomanNumeral.values()[2 * placeValue + 1];
        RomanNumeral romanNumeral3 = RomanNumeral.values()[2 * placeValue + 2];
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
        if (inputList.stream().anyMatch(input -> !Arrays.stream(RomanNumeral.values())
                        .map(RomanNumeral::getRomanValue)
                        .toList()
                        .contains(input))) {
            throw new InvalidNumberException("input contains strings that are no roman numerals");
        }
    }

    private static List<String> convertInputToList(String input) {
        return Arrays.stream(input.split(""))
                .collect(Collectors.toCollection(ArrayList::new));
    }

}
