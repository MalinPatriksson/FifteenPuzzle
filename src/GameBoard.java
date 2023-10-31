import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameBoard extends JFrame {
    ArrayList<JButton> buttons;
    JPanel puzzlePanel;
    JPanel newGamePanel;
    JButton emptyButton;
    JButton newGameButton;
    Font custom = new Font("Arial", Font.PLAIN, 15);
    Font custom1 = new Font("Arial", Font.BOLD, 20);

    GameBoard() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        buttons = new ArrayList<>();
        initializeSortedGame();

        setLayout(new BorderLayout());
        puzzlePanel = new JPanel(new GridLayout(4, 4));

        for (JButton button : buttons) {
            puzzlePanel.add(button);
        }
        add(puzzlePanel, BorderLayout.CENTER);
        ButtonFunction buttonFunction = new ButtonFunction(this);

        for(JButton button : buttons) {
            button.addActionListener(buttonFunction);
        }

        //shuffleButtons();
        newGame();

        setSize(350, 350);
        setLocationRelativeTo(null);
        setVisible(true);

    }

        // Metod för att testa meddelandet
        void initializeSortedGame() {
        for (int number = 0; number < 15; number++) {
            JButton button = new JButton(String.valueOf(number + 1));
            buttons.add(button);
            button.setBackground(Color.pink);
            button.setForeground(Color.WHITE);
            button.setFont(custom1);
            button.addActionListener(e -> {

            });
        }

        // Lägger till den tomma knappen
        emptyButton = new JButton("");
        buttons.add(emptyButton);
        emptyButton.setBackground(Color.WHITE);

    }
        // Metod för att shuffla knapparna
        void shuffleButtons () {
            Collections.shuffle(buttons);
            puzzlePanel.removeAll(); //Tar bort befintliga brickor från spelet
            for (JButton button : buttons) {
                puzzlePanel.add(button);
                if (button.getText().isEmpty()) {
                    emptyButton = button;
                }
            }
            puzzlePanel.revalidate(); // Uppdaterar panelen
            puzzlePanel.repaint(); // Tvingar
        }

        // Metod för nytt spel-knappen
        void newGame() {
            newGameButton = new JButton("Nytt spel");
            newGameButton.addActionListener(e -> shuffleButtons());
            newGameButton.setForeground(Color.DARK_GRAY);
            newGameButton.setFont(custom);

            newGamePanel = new JPanel();
            newGamePanel.add(newGameButton);
            add(newGamePanel, BorderLayout.SOUTH);
        }

    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard();
    }
}
