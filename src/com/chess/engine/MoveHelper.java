package com.chess.engine;

import com.chess.model.Piece;
import com.chess.model.PieceColor;
import com.chess.model.Position;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilitário para cálculo de movimentos retos e diagonais contínuos (Torre, Bispo, Rainha).
 */
public final class MoveHelper {

    private MoveHelper() {
        // Construtor privado para classe utilitária
    }

    /**
     * Calcula movimentos em raios direcionais até encontrar bordas ou obstáculos.
     */
    public static List<Position> getLinearMoves(Board board, Position currentPos, int[][] directions, PieceColor myColor) {
        List<Position> moves = new ArrayList<>();

        for (int[] dir : directions) {
            int r = currentPos.getRow() + dir[0];
            int c = currentPos.getColumn() + dir[1];

            while (board.isValid(r, c)) {
                Piece target = board.getPiece(r, c);
                if (target == null) {
                    moves.add(new Position(r, c));
                } else {
                    if (target.getColor() != myColor) {
                        moves.add(new Position(r, c)); // Captura válida
                    }
                    break; // Bloqueado por peça
                }
                r += dir[0];
                c += dir[1];
            }
        }
        return moves;
    }
}
