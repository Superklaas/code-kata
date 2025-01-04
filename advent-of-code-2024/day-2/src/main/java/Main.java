import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static final String REPORTS = "advent-of-code-2024/day-2/input/reports.txt";

    public static void main(String[] args) throws IOException {

        // convert input file into list of reports (each report is a list of integers)
        List<List<Integer>> reports = Files.readAllLines(Path.of(REPORTS)).stream()
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .map(strings -> strings.stream()
                        .map(Integer::parseInt)
                        .toList())
                .toList();

        /* PART ONE */
        List<List<Integer>> safeReports = reports.stream()
                .filter(Main::checkIfReportIsSafe)
                .collect(Collectors.toCollection(ArrayList::new));
        System.out.println(safeReports.size());

        /* PART TWO */
        List<List<Integer>> unsafeReports = new ArrayList<>(reports);
        unsafeReports.removeAll(safeReports);
        List<List<Integer>> safeReportsAfterShortening = unsafeReports.stream()
                .map(report -> IntStream.range(0, report.size())
                        .mapToObj(i -> {
                            List<Integer> shortenedReport = new ArrayList<>(report);
                            shortenedReport.remove(i);
                            return shortenedReport;
                        })
                        .filter(Main::checkIfReportIsSafe)
                        .findFirst()
                        .orElse(null))
                .filter(Objects::nonNull)
                .toList();
        safeReports.addAll(safeReportsAfterShortening);
        System.out.println(safeReports.size());

    }

    private static boolean checkIfReportIsSafe(List<Integer> report) {
        boolean increasing = report.get(1) > report.get(0);
        boolean oneDirection = true;
        boolean intervalOneToThree = true;
        for (int i = 1; i < report.size(); i++) {
            if ((increasing && report.get(i) < report.get(i - 1)) || (!increasing && report.get(i) > report.get(i - 1))) {
                oneDirection = false;
                break;
            }
            if (Math.abs(report.get(i) - report.get(i - 1)) > 3 || Math.abs(report.get(i) - report.get(i - 1)) == 0) {
                intervalOneToThree = false;
                break;
            }
        }
        return oneDirection && intervalOneToThree;
    }

}
