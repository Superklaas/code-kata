public class Application {

    public static void main(String[] args) {

        String originalWord = "meetmebythetree";
        String keyword = "ottoplakalp";
        System.out.println("Original word: " + originalWord);
        System.out.println("Keyword: " + keyword);

        Encoder encoder = new Encoder();
        String encodedWord = encoder.encode(originalWord, keyword);
        System.out.println("Encoded word: " + encodedWord);

        Decoder decoder = new Decoder();
        String decodedWord = decoder.decode(encodedWord, keyword);
        System.out.println("Decoded word: " + decodedWord);

    }

}
