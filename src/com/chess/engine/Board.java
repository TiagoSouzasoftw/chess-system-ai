package com.chess.engine;

import com.chess.model.Piece;
import com.chess.model.Position;

/**
 * Representa o tabuleiro 8x8 de xadrez e gerencia o estado da grade de casas.
 */
public class Board {

    public static final int ROWS = 8;
    public static final int COLUMNS = 8;

    private final Piece[][] pieces;

    public Board() {
        this.pieces = new Piece[ROWS][COLUMNS];
    }

    /**
     * Valida se uma determinada coordenada pertence aos limites do tabuleiro.
     */
    public boolean isValid(int row, int col) {
        return row >= 0 && row < ROWS && col >= 0 && col < COLUMNS;
    }

    public boolean isValid(Position position) {
        return position != null && isValid(position.getRow(), position.getColumn());
    }

    /**
     * Recupera a peça localizada em (row, col).
     */
    public Piece getPiece(int row, int col) {
        return isValid(row, col) ? pieces[row][col] : null;
    }

    /**
     * Recupera a peça localizada na posição indicada.
     */
    public Piece getPiece(Position position) {
        return position != null ? getPiece(position.getRow(), position.getColumn()) : null;
    }

    /**
     * Aloca uma peça em uma posição específica do tabuleiro.
     */
    public void setPiece(Position position, Piece piece) {
        if (isValid(position)) {
            pieces[position.getRow()][position.getColumn()] = piece;
        }
    }

    /**
     * Remove e retorna a peça em uma posição (se houver).
     */
    public Piece removePiece(Position position) {
        if (!isValid(position)) return null;
        Piece p = pieces[position.getRow()][position.getColumn()];
        pieces[position.getRow()][position.getColumn()] = null;
        return p;
    }
}
