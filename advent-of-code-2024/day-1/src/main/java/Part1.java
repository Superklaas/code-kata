import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

    public static void main(String[] args) throws IOException {

        // get numbers from left & right column in input document
        List<Integer> numbersLeft = Commons.getNumbersLeft();
        List<Integer> numbersRight = Commons.getNumbersRight();

        // list with distances between numbers on same line in two ordered lists
        List<Integer> distances = new ArrayList<>();
        for(int i  = 0; i < numbersLeft.size(); i++) {
            Integer left = numbersLeft.get(i);
            Integer right = numbersRight.get(i);
            distances.add(Math.abs(right - left));
        }

        // sum of all distances
        // convert Stream<Integer> into IntStream, in order to use terminal operation sum()
        int sum1 = distances.stream()
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(sum1);

    }

}
