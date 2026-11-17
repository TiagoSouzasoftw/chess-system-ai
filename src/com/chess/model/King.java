package com.chess.model;

import com.chess.engine.Board;
import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "K" : "k";
    }

    @Override
    public String getUnicodeSymbol() {
        return color == PieceColor.WHITE ? "♔" : "♚";
    }

    @Override
    public int getValue() {
        return 20000;
    }

    @Override
    public List<Position> getPossibleMoves(Board board, Position currentPosition) {
        List<Position> moves = new ArrayList<>();
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };

        for (int[] dir : directions) {
            int targetRow = currentPosition.getRow() + dir[0];
            int targetCol = currentPosition.getColumn() + dir[1];

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
