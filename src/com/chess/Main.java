package com.chess;

import com.chess.ui.ChessGUI;
import javax.swing.SwingUtilities;

/**
 * Ponto de entrada (Entry point) da aplicação.
 */
public class Main {
    public static void main(String[] args) {
        // Inicializa a interface gráfica na thread de eventos do Swing (EDT)
        SwingUtilities.invokeLater(ChessGUI::new);
    }
}
