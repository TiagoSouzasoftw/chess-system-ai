package com.chess.model;

/**
 * Representa um movimento do xadrez com origem, destino e pontuação associada (para IA).
 */
public class Move {

    private final Position from;
    private final Position to;
    private int score;

    public Move(Position from, Position to) {
        this.from = from;
        this.to = to;
        this.score = 0;
    }

    public Move(Position from, Position to, int score) {
        this.from = from;
        this.to = to;
        this.score = score;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return from.toChessNotation() + " -> " + to.toChessNotation();
    }
}
