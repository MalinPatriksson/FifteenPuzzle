import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ButtonFunction implements ActionListener {
    private JButton emptyButton;
    private ArrayList<JButton> buttons;
    private GameBoard gameBoard;

    public ButtonFunction(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        this.buttons = gameBoard.buttons;
        this.emptyButton = gameBoard.emptyButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (moveButton(clickedButton)) {
            gameBoard.puzzlePanel.removeAll();
            for (JButton movedButton : buttons) {
                gameBoard.puzzlePanel.add(movedButton);
            }
            gameBoard.puzzlePanel.revalidate();
            gameBoard.puzzlePanel.repaint();

            if (gameComplete()) {
                message();
            }
        }
    }

    // Initialiserar variabler som används för att lagra rad- och kolumnpositionen. Tomma värden (-1)
    public boolean moveButton(JButton clickedButton) {
        int clickedRow = -1;
        int clickedCol = -1;
        int emptyRow = -1;
        int emptyCol = -1;

        // Hitta rad- och kolumnpositionen för den klickande knappen och den tomma knappen
        for (int row = 0; row < 4; row++) { // Loopar igenom varje rad och kolumn
            for (int col = 0; col < 4; col++) {
                if (buttons.get(row * 4 + col) == clickedButton) {
                    clickedRow = row; // Spara radpositionen för den klickande knappen
                    clickedCol = col;

                    // Spara den tomma knappens position
                } else if (buttons.get(row * 4 + col).getText().isEmpty()) {
                    emptyRow = row;
                    emptyCol = col;

                }
            }
        }

        // Kontrollera om knappen kan flyttas (intill den tomma knappen)
        boolean canMove = false;
        if ((Math.abs(clickedRow - emptyRow) == 1 && clickedCol == emptyCol) ||
                (Math.abs(clickedCol - emptyCol) == 1 && clickedRow == emptyRow)) {
            canMove = true;

            // Flytta den tomma knappen till den klickande knappens position
            JButton tempButton = buttons.get(emptyRow * 4 + emptyCol);
            buttons.set(emptyRow * 4 + emptyCol, clickedButton);
            buttons.set(clickedRow * 4 + clickedCol, tempButton);
        }
        return canMove;
    }

    // Kontrollerar om spelet är slutfört, dvs sorterade i nummerordning.
    public boolean gameComplete() {
        int expectedNumber = 1;

        for (JButton button : buttons) {
            if (!button.getText().isEmpty()) { // Kontrollerar så knappen inte är tom (tomma knappen)
                int actualNumber = Integer.parseInt(button.getText());
                if (actualNumber != expectedNumber) { // Jämför det förväntade numret med det faktiska
                    return false; // Om de var olika returneras false
                }
                expectedNumber++; // Om de var lika påbörjas kontroll av nästa förväntade nummer
            }
        }

        return true; // När alla knappar har kontrollerats och inga fel uppstått, är spelet slutfört.
    }

    public void message() {
        JOptionPane.showMessageDialog(null, "Grattis, du vann!");

    }
}