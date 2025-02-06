import java.io.IOException;

public class GuardPositioner {

    public static final String INPUT = "advent-of-code-2024/day-6/input/input.txt";

    private final Grid grid;
    private final Guard guard;

    private static int guardStuckCounter;

    public static void main(String[] args) throws IOException {
        GuardPositioner guardPositioner = new GuardPositioner();
        guardPositioner.moveToBorder();
        guardPositioner.addObstruction();
    }

    public GuardPositioner() throws IOException {
        grid = new Grid(INPUT);
        guard = new Guard(grid);
    }

    void moveToBorder() {
        while (!guard.isAtBorder()) {
            guard.move();
        }
        System.out.println(guard.getCheckPointCounter());
    }

    void addObstruction() {
        grid.resetGrid();
        guard.resetGuard();
        for (int y = 0; y < grid.getCurrentGrid().length; y++) {
            for (int x = 0; x < grid.getCurrentGrid()[y].length; x++) {
                if (Grid.DOT.equals(grid.getCurrentGrid()[y][x])) {
                    grid.getCurrentGrid()[y][x] = Grid.HASHTAG;
                    boolean isGuardStuck = guard.checkIfGuardGetsStuck();
                    if (isGuardStuck) guardStuckCounter++;
                }
                grid.resetGrid();
                guard.resetGuard();
            }
        }
        System.out.println(guardStuckCounter);
    }

}
