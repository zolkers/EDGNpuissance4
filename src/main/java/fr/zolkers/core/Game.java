package fr.zolkers.core;

import java.util.List;

public class Game {

    public static boolean hasDrawn(Grid grid) {
        for (int x = 0; x < grid.getWidth(); x++) {
            if (grid.setCurrentTokenY(x) < grid.getHeight()) {
                return false;
            }
        }
        return true;
    }


    public static boolean hasWon(Grid grid, Token token) {
        int x = token.x();
        int y = token.y();
        char color = token.color();

        int[][] directions = {
                {0, 1},
                {1, 0},
                {1, 1},
                {1, -1},
                {-1, 1},
                {-1, -1},
                {-1, 0},
                {0, -1}
        };

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            int count = 1;

            count += countTokens(grid, x, y, dx, dy, color);
            count += countTokens(grid, x, y, -dx, -dy, color);

            int connect = 4;
            if (count >= connect) {
                return true;
            }
        }

        return false;
    }

    private static int countTokens(Grid grid, int x, int y, int dx, int dy, char color) {
        List<Token> tokenList = grid.getTokenList();
        int rowCount = grid.getHeight();
        int colCount = grid.getWidth();

        int nx = x + dx;
        int ny = y + dy;

        int count = 0;

        while (nx >= 0 && nx < colCount && ny >= 0 && ny < rowCount) {
            Token token = getTokenAtPosition(tokenList, nx, ny);
            if (token != null && token.color() == color) {
                count++;
                nx += dx;
                ny += dy;
            } else {
                break;
            }
        }

        return count;
    }

    private static Token getTokenAtPosition(List<Token> tokenList, int x, int y) {
        for (Token token : tokenList) {
            if (token.x() == x && token.y() == y) {
                return token;
            }
        }
        return null;
    }
}
