import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day2 {

    private static final String REPORTS = "advent-of-code-2024/day-2/input/reports.txt";

    public static void main(String[] args) throws IOException {

        List<List<Integer>> reports = Files.readAllLines(Path.of(REPORTS)).stream()
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .map(strings -> strings.stream()
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        // part one
        List<List<Integer>> safeReports = reports.stream()
                .filter(Day2::checkIfReportIsSafe)
                .toList();
        System.out.println(safeReports.size());

        // part two
        List<List<Integer>> safeReportsAfterShortening = new ArrayList<>();
        for (List<Integer> report : reports) {
            if (safeReports.contains(report)) continue;
            for (int i = 0; i < report.size(); i++) {
                List<Integer> shortenedReport = new ArrayList<>(report);
                shortenedReport.remove(i);
                if (checkIfReportIsSafe(shortenedReport)) {
                    safeReportsAfterShortening.add(shortenedReport);
                    break;
                }
            }
        }
        System.out.println(safeReports.size() + safeReportsAfterShortening.size());

    }

    private static boolean checkIfReportIsSafe(List<Integer> report) {
        boolean increasing = report.get(1) > report.get(0);
        for (int i = 1; i < report.size(); i++) {
            int diff = report.get(i) - report.get(i - 1);
            if ((increasing && diff <= 0) || (!increasing && diff >= 0) || Math.abs(diff) > 3) {
                return false;
            }
        }
        return true;
    }

}
