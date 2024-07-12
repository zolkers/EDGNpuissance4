package fr.zolkers.core.players;

public abstract class Player {

    private final char color;

    public Player(char color) {
        this.color = color;
    }

    public char getColor() {
        return color;
    }
}
