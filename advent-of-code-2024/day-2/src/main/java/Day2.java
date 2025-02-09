import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Day2 {

    private static final String REPORTS = "advent-of-code-2024/day-2/input/reports.txt";

    public static void main(String[] args) throws IOException {

        Day2 day2 = new Day2();

        List<List<Integer>> reports = day2.getReportsFromFile();

        // part one
        List<List<Integer>> safeReports = day2.getSafeReports(reports);
        System.out.println(safeReports.size());

        // part two
        List<List<Integer>> safeReportsAfterShortening = day2.getSafeReportsAfterShortening(reports, safeReports);
        System.out.println(safeReports.size() + safeReportsAfterShortening.size());

    }

    private List<List<Integer>> getReportsFromFile() throws IOException {
        return Files.readAllLines(Path.of(REPORTS)).stream()
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .map(strings -> strings.stream()
                        .map(Integer::parseInt)
                        .toList())
                .toList();
    }

    private List<List<Integer>> getSafeReports(List<List<Integer>> reports) {
        return reports.stream()
                .filter(this::checkIfReportIsSafe)
                .toList();
    }

    private List<List<Integer>> getSafeReportsAfterShortening(List<List<Integer>> reports, List<List<Integer>> safeReports) {
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
        return safeReportsAfterShortening;
    }

    private boolean checkIfReportIsSafe(List<Integer> report) {
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
