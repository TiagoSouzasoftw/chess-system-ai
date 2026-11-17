package com.chess.model;

import java.util.Objects;

/**
 * Representa as coordenadas (linha e coluna) de uma casa no tabuleiro (matriz 8x8).
 * Linhas: 0 a 7 (equivalentes às linhas 8 a 1 do xadrez).
 * Colunas: 0 a 7 (equivalentes às colunas a até h do xadrez).
 */
public class Position {

    private int row;
    private int column;

    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * Converte coordenadas de matriz interna em notação tradicional de xadrez (ex: "e4").
     */
    public String toChessNotation() {
        char colChar = (char) ('a' + column);
        int rowNumber = 8 - row;
        return "" + colChar + rowNumber;
    }

    /**
     * Cria uma Position a partir de notação tradicional de xadrez (ex: "e4").
     */
    public static Position fromChessNotation(String notation) {
        if (notation == null || notation.length() != 2) return null;
        char colChar = Character.toLowerCase(notation.charAt(0));
        char rowChar = notation.charAt(1);

        if (colChar < 'a' || colChar > 'h' || rowChar < '1' || rowChar > '8') return null;

        int col = colChar - 'a';
        int row = 8 - (rowChar - '0');
        return new Position(row, col);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }

    @Override
    public String toString() {
        return toChessNotation() + " (" + row + ", " + column + ")";
    }
}
