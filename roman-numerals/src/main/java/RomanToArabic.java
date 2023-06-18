import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RomanToArabic {

    private static final List<String> romanNumerals = List.of("I", "V", "X", "L", "C", "D", "M", "V̅", "X̅");

    public static void main(String[] args) {

        String input = "MCMXCIX";
        int output = 0;

        List<String> inputList = convertInputToList(input);

        validateInput(inputList);

        output = calculateArabicNumeral(output, inputList);

        System.out.println("roman number " + input + " converted to arabic number " + output);

    }

    private static int calculateArabicNumeral(int output, List<String> inputList) {
        while (inputList.size() != 0) {
            int index = switch (inputList.get(0)) {
                case "M" -> 3;
                case "D", "C" -> 2;
                case "L", "X" -> 1;
                default -> 0;
            };
            String firstRomanDigit = romanNumerals.get(2 * index);
            String secondRomanDigit = romanNumerals.get(2 * index + 1);
            String thirdRomanDigit = romanNumerals.get(2 * index + 2);
            if (secondRomanDigit.equals(inputList.get(0))) {
                output += 5 * Math.pow(10, index);
                inputList.remove(inputList.get(0));
            }
            if (firstRomanDigit.equals(inputList.get(0))) {
                if (thirdRomanDigit.equals(inputList.get(1))) {
                    output += 9 * Math.pow(10, index);
                    inputList.remove(inputList.get(0));
                    inputList.remove(inputList.get(0));
                } else if (secondRomanDigit.equals(inputList.get(1))) {
                    output += 4 * Math.pow(10, index);
                    inputList.remove(inputList.get(0));
                    inputList.remove(inputList.get(0));
                } else {
                    while (inputList.size() > 0 && firstRomanDigit.equals(inputList.get(0))) {
                        output += 1 * Math.pow(10, index);
                        inputList.remove(inputList.get(0));
                    }
                }
            }
        }
        return output;
    }

    private static void validateInput(List<String> inputList) {
        if (inputList.stream().anyMatch(string -> !romanNumerals.contains(string))) {
            throw new InvalidNumberException("input contains strings that are no roman numerals");
        }
    }

    private static List<String> convertInputToList(String input) {
        return new ArrayList<>(Arrays.stream(input.split("")).toList());
    }

}
