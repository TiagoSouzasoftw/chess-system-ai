package com.chess.model;

import com.chess.engine.Board;
import com.chess.engine.MoveHelper;
import java.util.List;

public class Queen extends Piece {

    public Queen(PieceColor color) {
        super(color);
    }

    @Override
    public String getSymbol() {
        return color == PieceColor.WHITE ? "Q" : "q";
    }

    @Override
    public String getUnicodeSymbol() {
        return color == PieceColor.WHITE ? "♕" : "♛";
    }

    @Override
    public int getValue() {
        return 900;
    }

    @Override
    public List<Position> getPossibleMoves(Board board, Position currentPosition) {
        int[][] directions = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
        return MoveHelper.getLinearMoves(board, currentPosition, directions, this.color);
    }
}
