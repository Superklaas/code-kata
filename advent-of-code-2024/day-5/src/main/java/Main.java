import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

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

        // get map pageOrderingRules: key pageNumber, value pageNumbers that can come after pageNumber
        Map<Integer, Set<Integer>> pageOrderingRules = new HashMap<>();
        for (String line : Files.readAllLines(Path.of(PAGE_ORDERING_RULES))) {
            List<Integer> pageOrderingRule = Arrays.stream(line.split("\\|"))
                    .map(Integer::parseInt)
                    .toList();
            if (!pageOrderingRules.containsKey(pageOrderingRule.get(0))) {
                pageOrderingRules.put(pageOrderingRule.get(0), new HashSet<>(Collections.singletonList(pageOrderingRule.get(1))));
            } else {
                pageOrderingRules.get(pageOrderingRule.get(0)).add(pageOrderingRule.get(1));
            }
        }

        /* PART ONE */

        // make list of updates with right order
        List<List<Integer>> rightOrderUpdates = new ArrayList<>();
        for (List<Integer> update : updates) {
            boolean isRightOrder = checkOrderUpdates(pageOrderingRules, update);
            if (isRightOrder) rightOrderUpdates.add(update);
        }

        // get middle element per update & make sum
        int sumMiddleElements = rightOrderUpdates.stream()
                .mapToInt(list -> list.get(list.size() / 2))
                .sum();
        System.out.println(sumMiddleElements);

        /* PART 2 */

        // get list wrongOrderUpdates
        List<List<Integer>> wrongOrderUpdates = new ArrayList<>(updates);
        wrongOrderUpdates.removeAll(rightOrderUpdates);

        // correct order & add to list correctedOrderUpdates
        List<List<Integer>> correctedOrderUpdates = new ArrayList<>();
        for (List<Integer> wrongOrderUpdate : wrongOrderUpdates) {
            List<Integer> correctedOrderUpdate = correctWrongOrderUpdate(pageOrderingRules, wrongOrderUpdate);
            correctedOrderUpdates.add(correctedOrderUpdate);
        }

        // get middle element per corrected update & make sum
        sumMiddleElements = correctedOrderUpdates.stream()
                .mapToInt(list -> list.get(list.size() / 2))
                .sum();
        System.out.println(sumMiddleElements);

    }

    /**
     * Correct order elements in wrongOrderUpdate recursively, until pageOrderingRules are met for all elements.
     */
    private static List<Integer> correctWrongOrderUpdate(Map<Integer, Set<Integer>> pageOrderingRules, List<Integer> wrongOrderUpdate) {
        List<Integer> correctedOrderUpdate;
        List<Integer> updateToCorrect = new ArrayList<>(wrongOrderUpdate);
        flipElementsAtIndexWrongElement(pageOrderingRules, updateToCorrect);
        boolean isRightOrder = checkOrderUpdates(pageOrderingRules, updateToCorrect);
        correctedOrderUpdate = isRightOrder ? updateToCorrect : correctWrongOrderUpdate(pageOrderingRules, updateToCorrect);
        return correctedOrderUpdate;
    }

    private static void flipElementsAtIndexWrongElement(Map<Integer, Set<Integer>> pageOrderingRules, List<Integer> updateToCorrect) {
        OUTER:
        for (int i = 0; i < updateToCorrect.size(); i++) {
            Integer pageNumber = updateToCorrect.get(i);
            Set<Integer> rulesForPageNumber = pageOrderingRules.get(pageNumber);
            for (int j = i + 1; j < updateToCorrect.size(); j++) {
                // at index wrong order, flip elements at given & previous index
                if (!rulesForPageNumber.contains(updateToCorrect.get(j))) {
                    Integer previousElement = updateToCorrect.get(j - 1);
                    Integer givenElement = updateToCorrect.get(j);
                    updateToCorrect.set(j - 1, givenElement);
                    updateToCorrect.set(j, previousElement);
                    break OUTER;
                }
            }
        }
    }

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

}
