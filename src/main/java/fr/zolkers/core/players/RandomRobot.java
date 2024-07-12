package fr.zolkers.core.players;

import fr.zolkers.core.Grid;
import fr.zolkers.core.Token;

import java.util.Random;

public class RandomRobot extends Player implements Robot {
    public final static int DIFFICULTY = 0;
    private final Random random = new Random();
    public RandomRobot(char color) {
        super(color);
    }
    public int move(Grid grid) {
        int x;
        do {
            x = random.nextInt(grid.getWidth());
        } while (!Token.isXvalid(x, grid));
        return x;
    }
}
