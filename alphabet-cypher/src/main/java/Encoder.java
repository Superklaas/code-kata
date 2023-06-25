import java.util.ArrayList;
import java.util.List;

public class Encoder {

    public String encode(String input, String keyword) {

        List<String> inputList = Utils.getInputList(input);
        List<String> keywordList = Utils.getKeywordList(input, keyword);

        List<String> encodedLetterList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            int rowIndex = Alphabet.valueOf(inputList.get(i).toUpperCase()).ordinal();
            int columnIndex = Alphabet.valueOf(keywordList.get(i).toUpperCase()).ordinal();
            int encodedLetterIndex = rowIndex + columnIndex;
            String encodedLetter;
            if (encodedLetterIndex < 26) {
                encodedLetter = Alphabet.values()[encodedLetterIndex].name();
            } else {
                encodedLetter = Alphabet.values()[encodedLetterIndex-26].name();
            }
            encodedLetterList.add(encodedLetter);
        }

        return String.join("",encodedLetterList).toLowerCase();

    }

}
