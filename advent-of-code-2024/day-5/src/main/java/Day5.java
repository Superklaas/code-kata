import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Day5 {

    public static final String PAGE_ORDERING_RULES = "advent-of-code-2024/day-5/input/page-ordering-rules.txt";
    public static final String UPDATES = "advent-of-code-2024/day-5/input/updates.txt";

    public static void main(String[] args) throws IOException {

        // get list updates
        List<List<Integer>> updates = Files.readAllLines(Path.of(UPDATES))
                .stream()
                .map(s -> Arrays.stream(s.split(","))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        // get map pageOrderingRules: key pageNumber, value set of pageNumbers that can follow pageNumber
        Map<Integer, Set<Integer>> pageOrderingRules = Files.readAllLines(Path.of(PAGE_ORDERING_RULES))
                .stream()
                .map(line -> Arrays.stream(line.split("\\|"))
                        .map(Integer::parseInt)
                        .toList())
                .collect(Collectors.toMap(
                        list -> list.get(0),
                        list -> new TreeSet<>(Collections.singletonList(list.get(1))),
                        (set1, set2) -> {
                            set1.addAll(set2);
                            return set1;
                        }
                ));
        pageOrderingRules.entrySet().forEach(System.out::println);

        /* PART ONE */

        // check order per update & make list of updates with right order
        List<List<Integer>> rightOrderUpdates = new ArrayList<>();
        for (List<Integer> update : updates) {
            boolean isRightOrder = checkOrderUpdates(pageOrderingRules, update);
            if (isRightOrder) rightOrderUpdates.add(update);
        }

        // get middle element per update & make sum
        int sumMiddleElements = calculateSumMiddleElements(rightOrderUpdates);
        System.out.println(sumMiddleElements);

        /* PART 2 */

        // get list wrongOrderUpdates
        List<List<Integer>> wrongOrderUpdates = new ArrayList<>(updates);
        wrongOrderUpdates.removeAll(rightOrderUpdates);

        // correct order wrongOrderUpdate & add corrected version to list correctedOrderUpdates
        List<List<Integer>> correctedOrderUpdates = new ArrayList<>();
        for (List<Integer> wrongOrderUpdate : wrongOrderUpdates) {
            List<Integer> correctedOrderUpdate = correctWrongOrderUpdate(pageOrderingRules, wrongOrderUpdate);
            correctedOrderUpdates.add(correctedOrderUpdate);
        }

        // get middle element per corrected order update & make sum
        sumMiddleElements = calculateSumMiddleElements(correctedOrderUpdates);
        System.out.println(sumMiddleElements);

    }

    /**
     * Check per element update if all following elements meet pageOrderingRules.
     */
    private static boolean checkOrderUpdates(Map<Integer, Set<Integer>> pageOrderingRules, List<Integer> update) {
        boolean isRightOrder = true;
        OUTER:
        for (int i = 0; i < update.size(); i++) {
            Integer pageNumber = update.get(i);
            Set<Integer> rulesForPageNumber = pageOrderingRules.get(pageNumber);
            for (int j = i + 1; j < update.size(); j++) {
                Integer followingPageNumber = update.get(j);
                if (!rulesForPageNumber.contains(followingPageNumber)) {
                    isRightOrder = false;
                    break OUTER;
                }
            }
        }
        return isRightOrder;
    }

    /**
     * Correct order elements in wrongOrderUpdate recursively, until pageOrderingRules are met for all elements.
     */
    private static List<Integer> correctWrongOrderUpdate(Map<Integer, Set<Integer>> pageOrderingRules, List<Integer> wrongOrderUpdate) {
        List<Integer> updateToCorrect = new ArrayList<>(wrongOrderUpdate);
        List<Integer> correctedUpdate = flipElementsAtIndexWrongElement(pageOrderingRules, updateToCorrect);
        boolean isRightOrder = checkOrderUpdates(pageOrderingRules, correctedUpdate);
        return isRightOrder
                ? correctedUpdate
                : correctWrongOrderUpdate(pageOrderingRules, correctedUpdate);
    }

    /**
     * When pageOrderingRule not found for given index, flip elements at given & previous index.
     */
    private static List<Integer> flipElementsAtIndexWrongElement(Map<Integer, Set<Integer>> pageOrderingRules, List<Integer> updateToCorrect) {
        OUTER:
        for (int i = 0; i < updateToCorrect.size(); i++) {
            Integer pageNumber = updateToCorrect.get(i);
            Set<Integer> rulesForPageNumber = pageOrderingRules.get(pageNumber);
            for (int j = i + 1; j < updateToCorrect.size(); j++) {
                if (!rulesForPageNumber.contains(updateToCorrect.get(j))) {
                    Integer previousElement = updateToCorrect.get(j - 1);
                    Integer givenElement = updateToCorrect.get(j);
                    updateToCorrect.set(j - 1, givenElement);
                    updateToCorrect.set(j, previousElement);
                    break OUTER;
                }
            }
        }
        return updateToCorrect;
    }

    /**
     * Get middle element per update & make sum of all middle elements.
     */
    private static int calculateSumMiddleElements(List<List<Integer>> updates) {
        return updates.stream()
                .map(list -> list.get(list.size() / 2))
                .mapToInt(Integer::intValue)
                .sum();
    }

}
