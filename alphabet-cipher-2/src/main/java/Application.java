public class Application {

    public static void main(String[] args) {

        CipherService cipherService = new CipherService();

        String input = "cometothepantryofthegrandsalon";
        String keyword = "scones";
        String output = cipherService.applyAlphabetCipher(input, keyword, CypherDirection.ENCODE);
        System.out.printf("String %s encoded to %s%n", input, output);

        String originalInput = cipherService.applyAlphabetCipher(output, keyword, CypherDirection.DECODE);
        System.out.printf("String %s decoded to %s%n", output, originalInput);

    }

}
