public class Application {

    public static void main(String[] args) {

        String input = "cometothepantryofthegrandsalon";
        String keyword = "scones";
        String output = applyAlphabetCipher(input, keyword, CypherDirection.ENCODE);
        System.out.println(output);

        String originalInput = applyAlphabetCipher(output, keyword, CypherDirection.DECODE);
        System.out.println(originalInput);

    }

    private static String applyAlphabetCipher(String input, String keyword, CypherDirection cypherDirection) {

        input = input.toUpperCase();
        keyword = keyword.toUpperCase();
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            int inputIndex = Alphabet.valueOf(String.valueOf(input.charAt(i))).ordinal();

            int keywordIndex;
            if (i >= keyword.length()) {
                keywordIndex = Alphabet.valueOf(String.valueOf(keyword.charAt(i % keyword.length()))).ordinal();
            } else {
                keywordIndex = Alphabet.valueOf(String.valueOf(keyword.charAt(i))).ordinal();
            }

            int outputIndex;
            if (CypherDirection.ENCODE == cypherDirection) {
                outputIndex = inputIndex + keywordIndex;
                if (outputIndex > 25) {
                    outputIndex = outputIndex - 26;
                }
            } else {
                outputIndex = inputIndex - keywordIndex;
                if (outputIndex < 0) {
                    outputIndex = outputIndex + 26;
                }
            }

            output.append(Alphabet.values()[outputIndex].name());

        }

        return output.toString().toLowerCase();

    }

}
