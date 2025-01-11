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

    public static final String PAGE_ORDERING_RULES = "advent-of-code-2024/day-5/src/input/page-ordering-rules.txt";
    public static final String UPDATES = "advent-of-code-2024/day-5/src/input/updates.txt";

    public static void main(String[] args) throws IOException {

        // convert updates.txt to list of integer lists
        List<List<Integer>> updates = Files.readAllLines(Path.of(UPDATES))
                .stream()
                .map(s -> Arrays.stream(s.split(","))
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        // convert page-ordering-rules.txt to map: key pageNumber, value pageNumbers that can come after pageNumber
        // improvement: call collect(Collectors.toMap(Function k, Function v, BinaryOperator m) on list pagerOrderingRules
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

        // get updates with right order
        List<List<Integer>> rightOrderUpdates = new ArrayList<>();
        for (List<Integer> update : updates) {
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
            if (isRightOrder) {
                rightOrderUpdates.add(update);
            }
        }

        // get middle element per update & make sum
        int sumMiddleElements = rightOrderUpdates.stream()
                .mapToInt(list -> list.get(list.size() / 2))
                .sum();
        System.out.println(sumMiddleElements);

        /* PART 2 */


    }

}
