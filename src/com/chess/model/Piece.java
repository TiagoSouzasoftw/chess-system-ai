package com.chess.model;

import com.chess.engine.Board;
import java.util.List;

/**
 * Classe abstrata base para todas as peças do xadrez.
 * Demonstra os pilares de POO: Herança, Polimorfismo e Encapsulamento.
 */
public abstract class Piece {

    protected final PieceColor color;

    public Piece(PieceColor color) {
        this.color = color;
    }

    public PieceColor getColor() {
        return color;
    }

    /**
     * Retorna o símbolo de caractere ASCII/Texto da peça.
     */
    public abstract String getSymbol();

    /**
     * Retorna o caractere visual Unicode oficial do xadrez.
     */
    public abstract String getUnicodeSymbol();

    /**
     * Valor posicional/material da peça usado pela IA para cálculo de utilidade.
     */
    public abstract int getValue();

    /**
     * Calcula e retorna a lista de todos os movimentos válidos da peça a partir de sua posição atual.
     */
    public abstract List<Position> getPossibleMoves(Board board, Position currentPosition);

    @Override
    public String toString() {
        return getSymbol();
    }
}
