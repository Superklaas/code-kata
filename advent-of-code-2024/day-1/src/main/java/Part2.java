import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class Part2 {

    public static void main(String[] args) throws IOException {

        // get numbers from left & right column in input document
        List<Integer> numbersLeft = Commons.getNumbersLeft();
        List<Integer> numbersRight = Commons.getNumbersRight();

        // distinct numbers left
        List<Integer> distinctNumbersLeft = numbersLeft.stream()
                .distinct()
                .toList();

        // create list with objects NumberOccurence & fill variable number
        List<NumberOccurence> numberOccurences = distinctNumbersLeft.stream()
                .map(NumberOccurence::new)
                .toList();

        // for each distinct number, check how many times in left
        // fill variable timesLeft per object NumberOccurence
        for(Integer distinctNumberLeft : distinctNumbersLeft) {
            long timesLeft = numbersLeft.stream()
                    .filter(i -> Objects.equals(i, distinctNumberLeft))
                    .count();
            numberOccurences.stream()
                    .filter(numberOccurence -> Objects.equals(numberOccurence.getNumber(), distinctNumberLeft))
                    .forEach(numberOccurence -> numberOccurence.setTimesLeft(timesLeft));
        }

        // for each distinct number, check how many times in right
        // fill variable timesRight per object NumberOccurence
        for(Integer distinctNumberLeft : distinctNumbersLeft) {
            long timesRight = numbersRight.stream()
                    .filter(i -> Objects.equals(i, distinctNumberLeft))
                    .count();
            numberOccurences.stream()
                    .filter(numberOccurence -> Objects.equals(numberOccurence.getNumber(), distinctNumberLeft))
                    .forEach(numberOccurence -> numberOccurence.setTimesRight(timesRight));
        }

        // get list only with number occurences that have similarities left - right
        List<NumberOccurence> similarities = numberOccurences.stream()
                .filter(numberOccurence -> numberOccurence.getTimesRight() != 0)
                .toList();
        System.out.println(similarities);

        // formula per similarity: number * timesLeft * timesRight
        // add products to get similarity score
        int similarityScore = 0;
        for (NumberOccurence similarity : similarities) {
            similarityScore += similarity.getNumber() * similarity.getTimesLeft() * similarity.getTimesRight();
        }
        System.out.println(similarityScore);

    }

}
