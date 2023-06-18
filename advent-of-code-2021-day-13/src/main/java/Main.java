import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String COORDINATES_FILE = "advent-of-code-2021-day-13/input/coordinates1.txt";
    public static final String FOLD_INSTRUCTIONS_FILE = "advent-of-code-2021-day-13/input/foldInstructions1.txt";

    public static void main(String[] args) throws IOException {

        List<int[]> coordinatesList = getCoordinatesListFromFile();

        printGrid(coordinatesList);

        List<String> foldInstructions = getFoldInstructionsFromFile();

        for (String foldInstruction : foldInstructions) {
            String foldAxis = foldInstruction.substring(0, 1);
            int foldLine = Integer.parseInt(foldInstruction.split("=")[1]);
            coordinatesList = fold(coordinatesList, foldAxis, foldLine);
        }

        printCoordinatesList(coordinatesList);

        printGrid(coordinatesList);

    }

    private static List<int[]> getCoordinatesListFromFile() throws FileNotFoundException {
        List<int[]> coordinatesList = new ArrayList<>();
        File coordinatesFile = new File(COORDINATES_FILE);
        Scanner scanner = new Scanner(coordinatesFile);
        while (scanner.hasNext()) {
            int[] coordinates = new int[2];
            String[] coordinatesString = scanner.nextLine().split(",");
            coordinates[0] = Integer.parseInt(coordinatesString[0]);
            coordinates[1] = Integer.parseInt(coordinatesString[1]);
            coordinatesList.add(coordinates);
        }
        return coordinatesList;
    }

    private static List<String> getFoldInstructionsFromFile() throws FileNotFoundException {
        List<String> foldInstructions = new ArrayList<>();
        File foldInstructionsFile = new File(FOLD_INSTRUCTIONS_FILE);
        Scanner scanner = new Scanner(foldInstructionsFile);
        while (scanner.hasNext()) {
            foldInstructions.add(scanner.nextLine().substring(11));
        }
        return foldInstructions;
    }

    private static List<int[]> fold(List<int[]> oldCoordinatesList, String foldAxis, int foldLine) {
        List<int[]> newCoordinatesList = new ArrayList<>();
        if ("y".equals(foldAxis)) {
            for (int[] oldCoordinates : oldCoordinatesList) {
                int coordinateX = oldCoordinates[0];
                int coordinateY = oldCoordinates[1];
                if (coordinateY < foldLine) {
                    if (newCoordinatesList.stream().noneMatch(coordinates -> coordinates[0] == oldCoordinates[0] && coordinates[1] == oldCoordinates[1])) {
                        newCoordinatesList.add(oldCoordinates);
                    }
                } else {
                    int[] newCoordinates = {coordinateX, 2 * foldLine - coordinateY};
                    if (newCoordinatesList.stream().noneMatch(coordinates -> coordinates[0] == newCoordinates[0] && coordinates[1] == newCoordinates[1])) {
                        newCoordinatesList.add(newCoordinates);
                    }
                }
            }
        } else {
            for (int[] oldCoordinates : oldCoordinatesList) {
                int coordinateX = oldCoordinates[0];
                int coordinateY = oldCoordinates[1];
                if (coordinateX < foldLine) {
                    if (newCoordinatesList.stream().noneMatch(coordinates -> coordinates[0] == oldCoordinates[0] && coordinates[1] == oldCoordinates[1])) {
                        newCoordinatesList.add(oldCoordinates);
                    }
                } else {
                    int[] newCoordinates = {2 * foldLine - coordinateX, coordinateY};
                    if (newCoordinatesList.stream().noneMatch(coordinates -> coordinates[0] == newCoordinates[0] && coordinates[1] == newCoordinates[1])) {
                        newCoordinatesList.add(newCoordinates);
                    }
                }
            }
        }
        return newCoordinatesList;
    }

    private static void printCoordinatesList(List<int[]> coordinatesList) {
        coordinatesList.stream()
                .map(Arrays::toString)
                .forEach(System.out::println);
        System.out.println("Size list coordinates: " + coordinatesList.size());
    }

    private static void printGrid(List<int[]> coordinatesList) {
        int[] maximums = getMaximums(coordinatesList);
        String[][] grid = composeGrid(coordinatesList, maximums);
        printGrid(grid, maximums);
    }

    private static int[] getMaximums(List<int[]> coordinatesList) {
        int[] maximums = new int[2];
        int xMax = 0;
        int yMax = 0;
        for (int[] coordinates : coordinatesList) {
            if (coordinates[0] > xMax) xMax = coordinates[0];
            if (coordinates[1] > yMax) yMax = coordinates[1];
        }
        maximums[0] = xMax;
        maximums[1] = yMax;
        return maximums;
    }

    private static String[][] composeGrid(List<int[]> coordinatesList, int[] maximums) {
        int xMax = maximums[0];
        int yMax = maximums[1];
        String[][] grid = new String[xMax + 1][yMax + 1];
        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                grid[x][y] = ".";
            }
        }
        for (int[] coordinates : coordinatesList) {
            grid[coordinates[0]][coordinates[1]] = "&";
        }
        return grid;
    }

    private static void printGrid(String[][] grid, int[] maximums) {
        int xMax = maximums[0];
        int yMax = maximums[1];
        for (int y = 0; y <= yMax; y++) {
            for (int x = 0; x <= xMax; x++) {
                System.out.print(grid[x][y]);
            }
            System.out.print("\n");
        }
    }

}


