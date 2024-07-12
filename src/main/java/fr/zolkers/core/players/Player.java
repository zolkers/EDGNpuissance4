package fr.zolkers.core.players;

public abstract class Player {

    private char color;

    public Player(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }
}
