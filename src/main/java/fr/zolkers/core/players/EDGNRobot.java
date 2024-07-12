package fr.zolkers.core.players;

import fr.zolkers.core.Grid;
import fr.zolkers.algorithm.EDGNRobotMCTS;

public class EDGNRobot extends Player implements Robot {

    public EDGNRobot(char color) {
        super(color);
    }

    @Override
    public int move(Grid grid) {
        EDGNRobotMCTS mcts = new EDGNRobotMCTS();
        return mcts.findBestMove(grid, this.getColor());
    }
}
