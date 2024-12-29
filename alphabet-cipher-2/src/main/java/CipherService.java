public class CipherService {


    public String applyAlphabetCipher(String input, String keyword, CipherDirection cipherDirection) {
        input = validateAndPrepareInput(input);
        keyword = validateAndPrepareKeyword(keyword);
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            int inputOrdinal = getInputOrdinal(input, i);
            int keywordOrdinal = getKeywordOrdinal(keyword, i);
            int outputIndex = getOutputIndex(inputOrdinal, keywordOrdinal, cipherDirection);
            String outputLetter = Alphabet.values()[outputIndex].name();
            output.append(outputLetter);
        }
        return output.toString().toLowerCase();
    }

    private String validateAndPrepareKeyword(String keyword) {
        if (keyword.isBlank()) {
            throw new IllegalArgumentException("keyword cannot be blank");
        }
        return keyword.trim().replace(" ","").toUpperCase();
    }

    private String validateAndPrepareInput(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException("input cannot be blank");
        }
        return input.trim().replace(" ","").toUpperCase();
    }

    private int getOutputIndex(int inputOrdinal, int keywordOrdinal, CipherDirection cipherDirection) {
        int outputIndex;
        if (CipherDirection.ENCODE == cipherDirection) {
            outputIndex = inputOrdinal + keywordOrdinal;
            if (outputIndex > 25) {
                outputIndex = outputIndex - 26;
            }
        } else {
            outputIndex = inputOrdinal - keywordOrdinal;
            if (outputIndex < 0) {
                outputIndex = outputIndex + 26;
            }
        }
        return outputIndex;
    }

    private int getKeywordOrdinal(String keyword, int index) {
        if (index >= keyword.length()) {
            int correctedIndex = index % keyword.length();
            Alphabet letter = Alphabet.valueOf(String.valueOf(keyword.charAt(correctedIndex)));
            return letter.ordinal();
        } else {
            Alphabet letter = Alphabet.valueOf(String.valueOf(keyword.charAt(index)));
            return letter.ordinal();
        }
    }

    private int getInputOrdinal(String input, int index) {
        Alphabet letter = Alphabet.valueOf(String.valueOf(input.charAt(index)));
        return letter.ordinal();
    }
}
