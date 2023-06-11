import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArabicToRoman {

    private static final List<String> romanNumerals = List.of("I", "V", "X", "L", "C", "D", "M", "V̅", "X̅");

    public static void main(String[] args) {

        String input = "1999";
        StringBuilder output = new StringBuilder();

        List<Integer> inputList = convertInputToIntegerList(input);
        validateInput(inputList);

        while (inputList.size() != 0) {
            inputList = calculateRomanNumeral(output, inputList, inputList.size() - 1);
        }

        System.out.println(output);

    }

    private static List<Integer> calculateRomanNumeral(StringBuilder output, List<Integer> inputList, int indexNumber) {
        String firstRomanDigit = romanNumerals.get(2*indexNumber);
        String secondRomanDigit = romanNumerals.get(2*indexNumber + 1);
        String thirdRomanDigit = romanNumerals.get(2*indexNumber + 2);
        Integer consideredNumber = inputList.get(0);
        if (consideredNumber / 9 == 1) {
            output.append(firstRomanDigit);
            output.append(thirdRomanDigit);
        } else if (consideredNumber / 5 == 1) {
            output.append(secondRomanDigit);
            output.append(firstRomanDigit.repeat(Math.max(0, consideredNumber - 5)));
        } else if (consideredNumber / 4 == 1) {
            output.append(firstRomanDigit).append(secondRomanDigit);
        } else {
            output.append(firstRomanDigit.repeat(Math.max(0, consideredNumber)));
        }
        inputList = removeConsideredNumberFromInputList(inputList);
        return inputList;
    }

    private static List<Integer> removeConsideredNumberFromInputList(List<Integer> inputList) {
        List<Integer> newInputList = new ArrayList<>();
        for (int i = 1; i < inputList.size(); i++) {
            newInputList.add(inputList.get(i));
        }
        inputList = newInputList;
        return inputList;
    }


    private static void validateInput(List<Integer> inputList) {
        if (inputList.size() > 4) {
            throw new InvalidNumberException("input can contain maximum 4 digits");
        }
        if (inputList.get(0) > 3) {
            throw new InvalidNumberException("input has to be smaller than 4000");
        }
        if (inputList.get(0) == 0) {
            throw new InvalidNumberException("first digit input cannot be zero");
        }
    }

    private static List<Integer> convertInputToIntegerList(String input) {
        List<Integer> inputList;
        try {
            inputList = Arrays.stream(input.split(""))
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new InvalidNumberException("input string can only contain numerals");
        }
        System.out.println(inputList);
        return inputList;
    }

}
