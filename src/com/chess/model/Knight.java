package com.chess.model;

import com.chess.engine.Board;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "N" : "n";
    }

    @Override
    public String getUnicodeSymbol() {
        return color == PieceColor.WHITE ? "♘" : "♞";
    }

    @Override
    public int getValue() {
        return 320;
    }

    @Override
    public List<Position> getPossibleMoves(Board board, Position currentPosition) {
        List<Position> moves = new ArrayList<>();
        int[][] jumps = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1},
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}
        };

        for (int[] jump : jumps) {
            int targetRow = currentPosition.getRow() + jump[0];
            int targetCol = currentPosition.getColumn() + jump[1];

            if (board.isValid(targetRow, targetCol)) {
                Piece targetPiece = board.getPiece(targetRow, targetCol);
                if (targetPiece == null || targetPiece.getColor() != this.color) {
                    moves.add(new Position(targetRow, targetCol));
                }
            }
        }
        return moves;
    }
}
