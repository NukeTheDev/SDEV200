import java.awt.*;
import java.io.File;
import javax.swing.*;

public class TitleScreen extends JFrame {
    private JLabel titleScreenLabel;
    private JButton newGameButton;
    private JButton resumeButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TitleScreen::new);
    }

    public TitleScreen() {
        setTitle("Main Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));
        titleScreenLabel = new JLabel("If You Dare!");
        newGameButton = new JButton("New Game");
        resumeButton = new JButton("Resume");

        // Check if a save file exists
        File saveFile = new File("/workspace/If_You_Dare!/src/gamesave.csv");
        if (!saveFile.exists()) {
            resumeButton.setEnabled(false);  // Disable resume if no save file exists
        } else {
            resumeButton.setEnabled(true);
        }

        // Button listeners
        newGameButton.addActionListener(e -> {
            if (saveFile.exists()) {
                int result = JOptionPane.showConfirmDialog(null,
                        "A game save file was found! Do you wish to resume the current game?",
                        "Game Save Found",
                        JOptionPane.YES_NO_OPTION);
                        setVisible(false);

                if (result == JOptionPane.NO_OPTION) {
                    setVisible(false);
                    new GameSetup().showGameModes();  // Start new game setup
                } else {
                    setVisible(false);
                    resumeGame();  // Load the saved game
                }
            } else {
                setVisible(false);
                new GameSetup().showGameModes();  // No save file, proceed with new game setup
            }
        });

        resumeButton.addActionListener(e -> resumeGame());

        add(titleScreenLabel);
        add(newGameButton);
        add(resumeButton);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Method to load a saved game
    private void resumeGame() {
        Game resumedGame = Game.loadGameFromCsv("gamesave.csv");
        resumedGame.playRound();
    }
}
