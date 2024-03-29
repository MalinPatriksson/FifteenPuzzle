import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GameBoard extends JFrame {
    protected ArrayList<JButton> buttons;
    protected JPanel puzzlePanel;
    protected JPanel newGamePanel;
    protected JButton emptyButton;
    private JButton newGameButton;
    private Font custom = new Font("Arial", Font.PLAIN, 15);
    private Font custom1 = new Font("Arial", Font.BOLD, 20);
    private Color coustumColor = new Color(128,234,255);

    GameBoard() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // En lista av knappar för att hålla spelbrickorna
        buttons = new ArrayList<>();
        // Initialisera spelet med sorterade knappar
        initializeSortedGame();

        // Skapar en JPanel
        puzzlePanel = new JPanel(new GridLayout(4, 4));

        // Lägger till varje knapp i panelen
        for (JButton button : buttons) {
            puzzlePanel.add(button);
        }
        add(puzzlePanel, BorderLayout.CENTER);

        // Skapar en instans av buttonFunction för att hantera knappinteraktioner
        ButtonFunction buttonFunction = new ButtonFunction(this);

        // Lägger till ActionListener för varje knapp i listan
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
            button.setBackground(coustumColor);
            button.setForeground(Color.WHITE);
            button.setFont(custom1);

        }

        // Lägger till den tomma knappen
        emptyButton = new JButton("");
        buttons.add(emptyButton);
        emptyButton.setBackground(Color.WHITE);

    }
        // Metod för att blanda knapparna
        public void shuffleButtons () {
            Collections.shuffle(buttons);
            puzzlePanel.removeAll(); //Tar bort befintliga knapparna från spelet
            for (JButton button : buttons) { // Lägger till de blandade knapparna
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
