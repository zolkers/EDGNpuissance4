package fr.zolkers.algorithme;

import fr.zolkers.core.Game;
import fr.zolkers.core.Grid;
import fr.zolkers.core.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EDGNRobotMCTS {

    private static final int SIMULATIONS = 10000;

    public int findBestMove(Grid grid, char player) {
        List<Integer> possibleMoves = getPossibleMoves(grid);

        for (int move : possibleMoves) {
            if (isWinningMove(grid, move, player)) {
                return move;
            }
        }

        char opponent = player == 'R' ? 'Y' : 'R';
        for (int move : possibleMoves) {
            if (isWinningMove(grid, move, opponent)) {
                return move;
            }
        }

        int bestMove = -1;
        double bestWinRate = -1;

        for (int move : possibleMoves) {
            double winRate = simulate(grid, move, player);
            if (winRate > bestWinRate) {
                bestWinRate = winRate;
                bestMove = move;
            }
        }

        return bestMove;
    }

    private boolean isWinningMove(Grid grid, int move, char player) {
        int y = grid.setCurrentTokenY(move);
        if (y == -1) return false;

        grid.getTokenList().add(new Token(move, y, player));
        boolean isWin = Game.hasWon(grid, new Token(move, y, player));
        grid.getTokenList().remove(grid.getTokenList().size() - 1);

        return isWin;
    }

    private List<Integer> getPossibleMoves(Grid grid) {
        List<Integer> possibleMoves = new ArrayList<>();
        for (int x = 0; x < grid.getWidth(); x++) {
            if (Token.isXvalid(x, grid)) {
                possibleMoves.add(x);
            }
        }
        return possibleMoves;
    }

    private double simulate(Grid grid, int move, char player) {
        int wins = 0;

        for (int i = 0; i < SIMULATIONS; i++) {
            if (simulateRandomGame(grid, move, player)) {
                wins++;
            }
        }

        return (double) wins / SIMULATIONS;
    }

    private boolean simulateRandomGame(Grid grid, int move, char player) {
        Grid simulationGrid = new Grid(grid.getWidth(), grid.getHeight());
        simulationGrid.getTokenList().addAll(grid.getTokenList());

        Random random = new Random();
        char currentPlayer = player;

        while (true) {
            int x = move;
            if (!Token.isXvalid(x, simulationGrid)) {
                return false;
            }

            int y = simulationGrid.setCurrentTokenY(x);
            simulationGrid.getTokenList().add(new Token(x, y, currentPlayer));

            if (Game.hasWon(simulationGrid, new Token(x, y, currentPlayer))) {
                return currentPlayer == player;
            }

            currentPlayer = currentPlayer == 'R' ? 'Y' : 'R';
            move = random.nextInt(simulationGrid.getWidth());
        }
    }
}
