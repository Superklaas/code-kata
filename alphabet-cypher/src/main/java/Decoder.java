import java.util.ArrayList;
import java.util.List;

public class Decoder {

    public String decode(String input, String keyword) {

        List<String> inputList = Utils.getInputList(input);
        List<String> keywordList = Utils.getKeywordList(input, keyword);

        List<String> decodedLetterList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            String decodedLetter;
            int rowIndex = Alphabet.valueOf(inputList.get(i).toUpperCase()).ordinal();
            int columnIndex = Alphabet.valueOf(keywordList.get(i).toUpperCase()).ordinal();
            int decodedLetterIndex = rowIndex - columnIndex;
            if (decodedLetterIndex < 0) {
                decodedLetter = Alphabet.values()[26 - Math.abs(decodedLetterIndex)].name();
            } else {
                decodedLetter = Alphabet.values()[decodedLetterIndex].name();
            }
            decodedLetterList.add(decodedLetter);
        }

        return String.join("", decodedLetterList).toLowerCase();

    }

}
