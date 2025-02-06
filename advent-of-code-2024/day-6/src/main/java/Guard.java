public class Guard {

    private final Grid grid;
    private int pointerX, pointerY, originalPointerX, originalPointerY;
    private Direction direction;
    private static int checkPointCounter;

    public Guard(Grid grid) {
        this.grid = grid;
        getOriginalPositionGuard();
        resetGuard();
    }

    private void getOriginalPositionGuard() {
        OUTER:
        for (int y = 0; y < grid.getCurrentGrid().length; y++) {
            for (int x = 0; x < grid.getCurrentGrid()[y].length; x++) {
                if ("^".equals(grid.getCurrentGrid()[y][x])) {
                    originalPointerX = x;
                    originalPointerY = y;
                    grid.getCurrentGrid()[y][x] = Grid.X;
                    grid.getOriginalGrid()[y][x] = Grid.X;
                    break OUTER;
                }
            }
        }
    }

    public void resetGuard() {
        pointerX = originalPointerX;
        pointerY = originalPointerY;
        direction = Direction.UP;
        checkPointCounter = 1;
    }

    public boolean isAtBorder() {
        return 0 == pointerX ||
                grid.getCurrentGrid()[0].length - 1 == pointerX ||
                0 == pointerY ||
                grid.getCurrentGrid().length - 1 == pointerY;
    }

    public void move() {
        int nextPositionY = pointerY + direction.getDy();
        int nextPositionX = pointerX + direction.getDx();
        String nextPosition = grid.getCurrentGrid()[nextPositionY][nextPositionX];
        switch (nextPosition) {
            case Grid.DOT -> {
                grid.getCurrentGrid()[nextPositionY][nextPositionX] = Grid.X;
                checkPointCounter++;
                pointerX = nextPositionX;
                pointerY = nextPositionY;
            }
            case Grid.X -> {
                pointerX = nextPositionX;
                pointerY = nextPositionY;
            }
            case Grid.HASHTAG -> direction = Direction.setNewDirection(direction);
        }
    }

    public boolean checkIfGuardGetsStuck() {
        for (int i = 0; i < grid.getNumberCellsInGrid(); i++) {
            move();
            if (isAtBorder()) return false;
        }
        return true;
    }

    public int getCheckPointCounter() {
        return checkPointCounter;
    }

}
