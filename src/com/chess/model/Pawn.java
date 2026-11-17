package com.chess.model;

import com.chess.engine.Board;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "P" : "p";
    }

    @Override
    public String getUnicodeSymbol() {
        return color == PieceColor.WHITE ? "♙" : "♟";
    }

    @Override
    public int getValue() {
        return 100;
    }

    @Override
    public List<Position> getPossibleMoves(Board board, Position currentPosition) {
        List<Position> moves = new ArrayList<>();
        int direction = (color == PieceColor.WHITE) ? -1 : 1;
        int startingRow = (color == PieceColor.WHITE) ? 6 : 1;

        // Avanço simples de 1 casa
        int nextRow = currentPosition.getRow() + direction;
        int col = currentPosition.getColumn();

        if (board.isValid(nextRow, col) && board.getPiece(nextRow, col) == null) {
            moves.add(new Position(nextRow, col));

            // Avanço duplo inicial se a primeira casa estiver livre
            int doubleRow = currentPosition.getRow() + 2 * direction;
            if (currentPosition.getRow() == startingRow && board.getPiece(doubleRow, col) == null) {
                moves.add(new Position(doubleRow, col));
            }
        }

        // Capturas diagonais
        for (int colOffset : new int[]{-1, 1}) {
            int targetCol = col + colOffset;
            if (board.isValid(nextRow, targetCol)) {
                Piece targetPiece = board.getPiece(nextRow, targetCol);
                if (targetPiece != null && targetPiece.getColor() != this.color) {
                    moves.add(new Position(nextRow, targetCol));
                }
            }
        }

        return moves;
    }
}
