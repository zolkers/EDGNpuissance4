package fr.zolkers;

import fr.zolkers.core.*;
import fr.zolkers.core.players.*;
import fr.zolkers.core.players.Robot;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    public static void main(String[] args) {
        Grid grid = new Grid(7, 6);
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setSize(1920,1080);
        frame.setLayout(null);
        JButton human = new JButton("Human");
        JButton robot = new JButton("Robot");
        final boolean[] isHumanOpponent = new boolean[1];
        human.setSize(300, 300);
        robot.setSize(300, 300);
        human.addActionListener(e -> {
            isHumanOpponent[0] = true;
            panel.setVisible(false);
        });
        robot.addActionListener(e -> {
            isHumanOpponent[0] = false;
            panel.setVisible(false);
        });
        panel.setSize(300, 300);
        panel.add(human);
        panel.add(robot);
        frame.add(panel);
        frame.setVisible(true);


        Player player1 = new Human('Y');
        Player player2;

        if (isHumanOpponent[0]) {
            player2 = new Human('R');
        } else {
            AtomicInteger robotChoice = new AtomicInteger(-1);
            JPanel robotList = SwingMainMenu.getjPanel(robotChoice);
            frame.add(robotList);

            if (robotChoice.get() == 1) {
                player2 = new RandomRobot('R');
            } else {
                player2 = new EDGNRobot('R');
            }
        }

        int randomPlayer = (int) Math.round( Math.random());

        Player currentPlayer;
        if(randomPlayer == 0) {
            currentPlayer = player1;
        } else {
            currentPlayer = player2;
        }
        grid.renderGrid(frame);
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
            grid.renderGrid(frame);

            if (Game.hasWon(grid, currentToken)) {
                System.out.println(color + " a gagné la partie");
                break;
            } else if (Game.hasDrawn(grid)) {
                System.out.println("DRAW");
                break;
            }

            currentPlayer = (currentPlayer == player1) ? player2 : player1;
        }
    }


}