package com.chess.ai;

import com.chess.engine.Board;
import com.chess.engine.ChessMatch;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.PieceColor;

import java.util.List;
import java.util.Random;

/**
 * Inteligência Artificial para o jogo de xadrez.
 * Suporta três modos de dificuldade simulando ratings:
 * - Nível 1 (~800 ELO): Decisão rápida/aleatória.
 * - Nível 2 (~1300 ELO): Algoritmo guloso baseado em avaliação de material.
 * - Nível 3 (~1800 ELO): Minimax com previsão e poda Alfa-Beta.
 */
public class ChessAI {

    private final Random random = new Random();

    /**
     * Calcula e escolhe a melhor jogada para a cor indicada conforme a dificuldade.
     */
    public Move findBestMove(ChessMatch match, PieceColor aiColor, int difficulty) {
        List<Move> legalMoves = match.getAllLegalMoves(aiColor);
        if (legalMoves.isEmpty()) {
            return null;
        }

        switch (difficulty) {
            case 1:
                return findRandomMove(legalMoves);
            case 2:
                return findGreedyMove(match.getBoard(), legalMoves);
            case 3:
            default:
                return findMinimaxMove(match, aiColor, 2);
        }
    }

    /**
     * Dificuldade 1: Seleciona aleatoriamente um movimento legal.
     */
    private Move findRandomMove(List<Move> moves) {
        return moves.get(random.nextInt(moves.size()));
    }

    /**
     * Dificuldade 2: Escolhe a jogada com maior ganho imediato de material capturado.
     */
    private Move findGreedyMove(Board board, List<Move> moves) {
        Move bestMove = moves.get(0);
        int maxGain = -1;

        for (Move move : moves) {
            Piece target = board.getPiece(move.getTo());
            int gain = (target != null) ? target.getValue() : 0;
            if (gain > maxGain) {
                maxGain = gain;
                bestMove = move;
            }
        }
        return bestMove;
    }

    /**
     * Dificuldade 3: Algoritmo Minimax com poda Alfa-Beta para antecipação de lances.
     */
    private Move findMinimaxMove(ChessMatch match, PieceColor aiColor, int depth) {
        Board board = match.getBoard();
        List<Move> moves = match.getAllLegalMoves(aiColor);

        Move bestMove = moves.get(0);
        int bestScore = Integer.MIN_VALUE + 1;

        for (Move move : moves) {
            Piece mover = board.getPiece(move.getFrom());
            Piece captured = board.getPiece(move.getTo());

            // Simula o movimento
            board.setPiece(move.getTo(), mover);
            board.setPiece(move.getFrom(), null);

            int score = minimax(match, depth - 1, false, aiColor, Integer.MIN_VALUE + 1, Integer.MAX_VALUE - 1);

            // Desfaz a simulação
            board.setPiece(move.getFrom(), mover);
            board.setPiece(move.getTo(), captured);

            if (score > bestScore) {
                bestScore = score;
                bestMove = move;
            }
        }
        return bestMove;
    }

    private int minimax(ChessMatch match, int depth, boolean isMaximizing, PieceColor aiColor, int alpha, int beta) {
        if (depth == 0) {
            return evaluateBoard(match.getBoard(), aiColor);
        }

        Board board = match.getBoard();
        PieceColor turn = isMaximizing ? aiColor : aiColor.getOpposite();
        List<Move> legalMoves = match.getAllLegalMoves(turn);

        if (legalMoves.isEmpty()) {
            return evaluateBoard(board, aiColor);
        }

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE + 1;
            for (Move move : legalMoves) {
                Piece mover = board.getPiece(move.getFrom());
                Piece captured = board.getPiece(move.getTo());

                board.setPiece(move.getTo(), mover);
                board.setPiece(move.getFrom(), null);

                int eval = minimax(match, depth - 1, false, aiColor, alpha, beta);

                board.setPiece(move.getFrom(), mover);
                board.setPiece(move.getTo(), captured);

                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) break; // Poda Alfa-Beta
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE - 1;
            for (Move move : legalMoves) {
                Piece mover = board.getPiece(move.getFrom());
                Piece captured = board.getPiece(move.getTo());

                board.setPiece(move.getTo(), mover);
                board.setPiece(move.getFrom(), null);

                int eval = minimax(match, depth - 1, true, aiColor, alpha, beta);

                board.setPiece(move.getFrom(), mover);
                board.setPiece(move.getTo(), captured);

                minEval = Math.min(minEval, eval);
                beta = Math.min(beta, eval);
                if (beta <= alpha) break; // Poda Alfa-Beta
            }
            return minEval;
        }
    }

    /**
     * Função de avaliação heurística do estado material do tabuleiro.
     */
    private int evaluateBoard(Board board, PieceColor aiColor) {
        int score = 0;
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null) {
                    if (p.getColor() == aiColor) {
                        score += p.getValue();
                    } else {
                        score -= p.getValue();
                    }
                }
            }
        }
        return score;
    }
}
