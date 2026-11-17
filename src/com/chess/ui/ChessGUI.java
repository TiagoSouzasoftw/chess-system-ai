package com.chess.ui;

import com.chess.ai.ChessAI;
import com.chess.engine.Board;
import com.chess.engine.ChessMatch;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.PieceColor;
import com.chess.model.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface Gráfica Interativa em Java Swing (GUI) para o jogo de Xadrez.
 * Suporta visualização de tabuleiro, seleção por clique, feedback visual e partidas contra IA.
 */
public class ChessGUI extends JFrame {

    private final JButton[][] squares = new JButton[Board.ROWS][Board.COLUMNS];
    private ChessMatch match;
    private final ChessAI ai;
    private Position selectedPosition = null;

    private JLabel statusLabel;
    private JComboBox<String> difficultyCombo;

    private static final Color LIGHT_SQUARE = new Color(240, 217, 181);
    private static final Color DARK_SQUARE = new Color(181, 136, 99);
    private static final Color SELECTED_COLOR = new Color(246, 246, 105);

    public ChessGUI() {
        super("Chess System with AI - Santander Bootcamp & DIO");
        this.match = new ChessMatch();
        this.ai = new ChessAI();

        initializeUI();
    }

    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 720);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel Superior: Controles
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        topPanel.setBackground(new Color(235, 235, 235));

        topPanel.add(new JLabel("Dificuldade IA:"));
        String[] difficulties = {"1 - Fácil (~800)", "2 - Médio (~1300)", "3 - Difícil (~1800)"};
        difficultyCombo = new JComboBox<>(difficulties);
        topPanel.add(difficultyCombo);

        JButton restartButton = new JButton("Nova Partida");
        restartButton.addActionListener(e -> resetGame());
        topPanel.add(restartButton);

        add(topPanel, BorderLayout.NORTH);

        // Painel Central: Grade 8x8 do Tabuleiro
        JPanel boardPanel = new JPanel(new GridLayout(Board.ROWS, Board.COLUMNS));
        Font chessFont = new Font("SansSerif", Font.BOLD, 36);

        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                JButton btn = new JButton();
                btn.setFont(chessFont);
                btn.setFocusPainted(false);
                btn.setOpaque(true);
                btn.setBorderPainted(false);

                final int row = r;
                final int col = c;
                btn.addActionListener(new CellClickListener(row, col));

                squares[r][c] = btn;
                boardPanel.add(btn);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        // Painel Inferior: Barra de status
        statusLabel = new JLabel("Sua vez de jogar (Brancas)", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        statusLabel.setPreferredSize(new Dimension(600, 35));
        add(statusLabel, BorderLayout.SOUTH);

        refreshBoardView();
        setVisible(true);
    }

    private void resetGame() {
        this.match = new ChessMatch();
        this.selectedPosition = null;
        statusLabel.setText("Partida reiniciada! Sua vez (Brancas).");
        refreshBoardView();
    }

    private void refreshBoardView() {
        Board board = match.getBoard();
        for (int r = 0; r < Board.ROWS; r++) {
            for (int c = 0; c < Board.COLUMNS; c++) {
                JButton btn = squares[r][c];
                Piece piece = board.getPiece(r, c);

                if (piece != null) {
                    btn.setText(piece.getUnicodeSymbol());
                    btn.setForeground(piece.getColor() == PieceColor.WHITE ? Color.WHITE : new Color(30, 30, 30));
                } else {
                    btn.setText("");
                }

                if (selectedPosition != null && selectedPosition.getRow() == r && selectedPosition.getColumn() == c) {
                    btn.setBackground(SELECTED_COLOR);
                } else {
                    btn.setBackground((r + c) % 2 == 0 ? LIGHT_SQUARE : DARK_SQUARE);
                }
            }
        }
    }

    private class CellClickListener implements ActionListener {
        private final int row, col;

        public CellClickListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (match.isGameOver() || match.getCurrentTurn() != PieceColor.WHITE) {
                return;
            }

            Position clickedPosition = new Position(row, col);

            if (selectedPosition == null) {
                Piece clickedPiece = match.getBoard().getPiece(clickedPosition);
                if (clickedPiece != null && clickedPiece.getColor() == PieceColor.WHITE) {
                    selectedPosition = clickedPosition;
                    refreshBoardView();
                }
            } else {
                if (selectedPosition.equals(clickedPosition)) {
                    selectedPosition = null;
                    refreshBoardView();
                    return;
                }

                boolean success = match.performMove(selectedPosition, clickedPosition);
                selectedPosition = null;
                refreshBoardView();

                if (success) {
                    if (match.isGameOver()) {
                        statusLabel.setText("Fim de Jogo! Você venceu!");
                        return;
                    }

                    statusLabel.setText("Computador pensando...");

                    int difficulty = difficultyCombo.getSelectedIndex() + 1;
                    Timer timer = new Timer(350, evt -> {
                        Move aiMove = ai.findBestMove(match, PieceColor.BLACK, difficulty);
                        if (aiMove != null) {
                            match.performMove(aiMove.getFrom(), aiMove.getTo());
                        }
                        refreshBoardView();

                        if (match.isGameOver()) {
                            statusLabel.setText("Fim de Jogo! O computador venceu!");
                        } else {
                            statusLabel.setText("Sua vez (Brancas)");
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                }
            }
        }
    }
}
