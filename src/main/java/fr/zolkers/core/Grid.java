package fr.zolkers.core;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Grid {

    public List<Token> tokenList = new ArrayList<>();
    private final int width;
    private final int height;

    public Grid(int width, int height){
        this.width = width;
        this.height = height;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int setCurrentTokenY(int x) {
        if (tokenList.isEmpty()) {
            return 0;
        }

        int maxYfromList = -1;
        for (Token token : tokenList) {
            if(token.x() == x && token.y() > maxYfromList) {
                maxYfromList = token.y();
            }
        }

        if(maxYfromList == -1) {
            return 0;
        }
        return maxYfromList + 1;
    }

    public void renderGrid(JFrame frame) {

        char[][] grid = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                grid[i][j] = '.';
            }
        }

        for (Token token : tokenList) {
            int x = token.x();
            int y = token.y();
            grid[y][x] = token.color();
        }

        StringBuilder sb = new StringBuilder();
        for (int i = height - 1; i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                sb.append(grid[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
