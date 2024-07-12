package fr.zolkers.core;

import java.util.Scanner;

public record Token(int x, int y, char color) {

    public static int setX(Grid grid) {
        Scanner scanner = new Scanner(System.in);
        int xAxis;
        do {
            int width = grid.getWidth() - 1;
            System.out.println("Entrer une valeur entre 0 et " + width + ":");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalide, recommence EDGN:");
                scanner.next();
            }
            xAxis = scanner.nextInt();

        } while ((xAxis < 0 || xAxis > grid.getWidth() - 1) && !isXvalid(xAxis, grid));

        return xAxis;
    }

    public static boolean isXvalid(int x, Grid grid) {
        if (grid.tokenList.isEmpty()) {
            return true;
        }
        int targetY = grid.getHeight() - 1;
        for (Token token : grid.tokenList) {
            if (token.x() == x && token.y() == targetY) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return Character.toString(color);
    }
}
