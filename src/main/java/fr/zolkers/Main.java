package fr.zolkers;

import fr.zolkers.core.*;
import fr.zolkers.core.players.*;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Contre humain ? : true, Contre robot ? : false ");
        boolean isHumanOpponent = scanner.nextBoolean();

        Grid grid = new Grid(7, 6);
        Player player1 = new Human('Y');
        Player player2;

        System.out.println("Choisissez le type de robot (1: RandomRobot, 2: EDGNRobot): ");
        int robotChoice = scanner.nextInt();

        if (isHumanOpponent) {
            player2 = new Human('R');
        } else {
            if (robotChoice == 1) {
                player2 = new RandomRobot('R');
            } else {
                player2 = new EDGNRobot('R');
            }
        }

        Player currentPlayer = player1;
        while (true) {
            int x;
            if (currentPlayer instanceof Robot) {
                x = ((Robot) currentPlayer).move(grid);
            } else {
                x = Token.setX(grid);
            }

            System.out.println("x : " + x);
            int y = grid.setCurrentTokenY(x);
            char color = currentPlayer.getColor();

            Token currentToken = new Token(x, y, color);
            grid.tokenList.add(currentToken);
            grid.renderGrid();

            if (Game.hasWon(grid, currentToken)) {
                System.out.println(color + " a gagné la partie");
                break;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }
}