package com.chess.model;

/**
 * Representa as cores das peças no jogo de xadrez.
 */
public enum PieceColor {
    WHITE,
    BLACK;

    /**
     * Retorna a cor adversária.
     */
    public PieceColor getOpposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
