package com.chess.engine;

import com.chess.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Orquestra as regras da partida de xadrez:
 * controle de turnos, execução de movimentos, capturas, promoções e fim de jogo.
 */
public class ChessMatch {

    private final Board board;
    private PieceColor currentTurn;
    private boolean gameOver;

    public ChessMatch() {
        this.board = new Board();
        this.currentTurn = PieceColor.WHITE;
        this.gameOver = false;
        setupInitialBoard();
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    /**
     * Posiciona as 32 peças nas suas casas oficiais de início de jogo.
     */
    private void setupInitialBoard() {
        // Peças Pretas (Linhas 0 e 1)
        board.setPiece(new Position(0, 0), new Rook(PieceColor.BLACK));
        board.setPiece(new Position(0, 1), new Knight(PieceColor.BLACK));
        board.setPiece(new Position(0, 2), new Bishop(PieceColor.BLACK));
        board.setPiece(new Position(0, 3), new Queen(PieceColor.BLACK));
        board.setPiece(new Position(0, 4), new King(PieceColor.BLACK));
        board.setPiece(new Position(0, 5), new Bishop(PieceColor.BLACK));
        board.setPiece(new Position(0, 6), new Knight(PieceColor.BLACK));
        board.setPiece(new Position(0, 7), new Rook(PieceColor.BLACK));
        for (int c = 0; c < 8; c++) {
            board.setPiece(new Position(1, c), new Pawn(PieceColor.BLACK));
        }

        // Peças Brancas (Linhas 6 e 7)
        board.setPiece(new Position(7, 0), new Rook(PieceColor.WHITE));
        board.setPiece(new Position(7, 1), new Knight(PieceColor.WHITE));
        board.setPiece(new Position(7, 2), new Bishop(PieceColor.WHITE));
        board.setPiece(new Position(7, 3), new Queen(PieceColor.WHITE));
        board.setPiece(new Position(7, 4), new King(PieceColor.WHITE));
        board.setPiece(new Position(7, 5), new Bishop(PieceColor.WHITE));
        board.setPiece(new Position(7, 6), new Knight(PieceColor.WHITE));
        board.setPiece(new Position(7, 7), new Rook(PieceColor.WHITE));
        for (int c = 0; c < 8; c++) {
            board.setPiece(new Position(6, c), new Pawn(PieceColor.WHITE));
        }
    }

    /**
     * Executa uma jogada no tabuleiro validando as regras do jogo.
     * @return true se o movimento foi legal e executado com sucesso; false caso contrário.
     */
    public boolean performMove(Position from, Position to) {
        if (gameOver) return false;

        Piece movingPiece = board.getPiece(from);
        if (movingPiece == null || movingPiece.getColor() != currentTurn) {
            return false;
        }

        List<Position> legalMoves = movingPiece.getPossibleMoves(board, from);
        if (!legalMoves.contains(to)) {
            return false;
        }

        Piece capturedPiece = board.getPiece(to);
        if (capturedPiece instanceof King) {
            this.gameOver = true;
        }

        // Efetua a transição no tabuleiro
        board.setPiece(to, movingPiece);
        board.setPiece(from, null);

        // Promoção simples de Peão ao alcançar a última linha adversária
        if (movingPiece instanceof Pawn) {
            if ((movingPiece.getColor() == PieceColor.WHITE && to.getRow() == 0) ||
                (movingPiece.getColor() == PieceColor.BLACK && to.getRow() == 7)) {
                board.setPiece(to, new Queen(movingPiece.getColor()));
            }
        }

        // Alterna o turno
        currentTurn = currentTurn.getOpposite();
        return true;
    }

    /**
     * Retorna todos os movimentos legais possíveis de uma determinada cor.
     */
    public List<Move> getAllLegalMoves(PieceColor color) {
        List<Move> moves = new ArrayList<>();
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                Piece p = board.getPiece(r, c);
                if (p != null && p.getColor() == color) {
                    Position from = new Position(r, c);
                    for (Position to : p.getPossibleMoves(board, from)) {
                        moves.add(new Move(from, to));
                    }
                }
            }
        }
        return moves;
    }
}
