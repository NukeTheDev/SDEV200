import java.io.*;
import java.util.List;
import javax.swing.*;

public class Game {
    private Mode gameMode;
    private Difficulty difficulty;
    protected List<Player> players;
    private List<DareCard> dareCards;
    protected boolean gameActive = true;
    protected int pointThreshold = 100; // Threshold for classic mode

    public Game(Mode gameMode, Difficulty difficulty, List<Player> players, List<DareCard> dareCards) {
        this.gameMode = gameMode;
        this.difficulty = difficulty;
        this.players = players;
        this.dareCards = dareCards;
    }


    public void playRound() {
        for (Player currentPlayer : players) {
            if (currentPlayer.takeTurn()) {
                JOptionPane.showMessageDialog(null, "CPU is taking their turn. Please wait...");
                cpuTurn();
                playRound();
            } else {
                DareCard dare = getRandomDare();
                int response = JOptionPane.showConfirmDialog(
                        null,
                        currentPlayer.getPlayerName() + ", your dare is: " + dare.toString() + "\nDo you accept the dare?",
                        "Dare Challenge",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(null, currentPlayer.getPlayerName() + " accepted the dare!");
                    

                } else if (response == JOptionPane.NO_OPTION) {
                    int forfeitResponse = JOptionPane.showConfirmDialog(null,
                            "Do you want to forfeit the game?",
                            "Forfeit",
                            JOptionPane.YES_NO_OPTION);
                    if (forfeitResponse == JOptionPane.YES_OPTION) {
                        forfeit(currentPlayer);
                    } else if (response == JOptionPane.NO_OPTION) {
                        playRound();
                    }
                }
            }
        }
    }

    private DareCard getRandomDare() {
        int randomIndex = (int) (Math.random() * dareCards.size());
        return dareCards.get(randomIndex);
    }

    private void cpuTurn() {
        System.out.println("CPU took their turn.");
    }

    private void forfeit(Player player) {
        JOptionPane.showMessageDialog(null, player.getPlayerName() + " has forfeited the game!");
        players.remove(player);
        if (players.size() == 1) {
            JOptionPane.showMessageDialog(null, players.get(0).getPlayerName() + " wins the game!");
            System.exit(0);
        }
        else 
            playRound();
    }

    public static Game loadGameFromCsv(String fileName) {
        // Load game state from CSV
        // Implement CSV reading and game state restoration logic here
        return null;
    }

    public void saveGameToCsv(String fileName) {
        // Save game state to CSV
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // Write the game state to the CSV file
            // Write gameMode, difficulty, player states, etc.
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
