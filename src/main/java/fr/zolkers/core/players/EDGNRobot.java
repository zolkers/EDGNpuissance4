package fr.zolkers.core.players;

import fr.zolkers.core.Grid;
import fr.zolkers.core.players.algorithme.MCTS;

public class EDGNRobot extends Player implements Robot {

    public EDGNRobot(char color) {
        super(color);
    }

    @Override
    public int move(Grid grid) {
        MCTS mcts = new MCTS();
        return mcts.findBestMove(grid, this.getColor());
    }
}
