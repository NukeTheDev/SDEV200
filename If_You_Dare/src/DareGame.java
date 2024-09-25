import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DareGame {
    private List<Player> players;
    private List<DareCard> dareCards;
    private int currentPlayerIndex;
    private String gameMode;
    private Timer timer;
    private int timeLimit;

    public DareGame() {
        players = new ArrayList<>();
        dareCards = DareLoader.loadDareCards(); // Load dare cards from a file or predefined source
        currentPlayerIndex = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DareGame game = new DareGame();
            game.startGame();
        });
    }

    private void startGame() {
        chooseGameMode();
        registerPlayers();
        playGame();
    }

    private void chooseGameMode() {
        String[] options = {"Classic", "Advanced", "Time Attack"};
        gameMode = (String) JOptionPane.showInputDialog(null, "Choose Game Mode:",
                "Game Mode Selection", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
    }

    private void registerPlayers() {
        int numLocalPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of Local Players:"));
        for (int i = 0; i < numLocalPlayers; i++) {
            String playerName = JOptionPane.showInputDialog("Enter name for Player " + (i + 1) + ":");
            players.add(new Player(playerName));
        }

        int numCPUPlayers = Integer.parseInt(JOptionPane.showInputDialog("Enter number of CPU Players:"));
        for (int i = 0; i < numCPUPlayers; i++) {
            players.add(new CPUPlayer());
        }
    }

    private void playGame() {
        while (getActivePlayersCount() > 1) {
            Player currentPlayer = players.get(currentPlayerIndex);
            if (!currentPlayer.isActive()) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            DareCard dareCard = getRandomDareCard();
            int response = JOptionPane.showConfirmDialog(null,
                    "Dare: " + dareCard.getQuestion() + "\nDo you take the dare?", "Dare Challenge",
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                startDareTimer(dareCard);
                // Wait for the player to complete the dare
                // Here you would handle the completion logic
                completeDare(currentPlayer, dareCard); // Handle success
            } else {
                JOptionPane.showMessageDialog(null, currentPlayer.getPlayerName() + " skipped the dare.");
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        declareWinner();
    }

    private void startDareTimer(DareCard dareCard) {
        if (gameMode.equals("Time Attack")) {
            timeLimit = 30; // Set time limit for the dare
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeLimit--;
                    if (timeLimit <= 0) {
                        timer.stop();
                        // Handle timeout logic
                        JOptionPane.showMessageDialog(null, "Time's up! You failed the dare.");
                    }
                }
            });
            timer.start();
        }
    }

    private void completeDare(Player player, DareCard dareCard) {
        if (timer != null && timer.isRunning()) {
            timer.stop(); // Stop the timer if it was running
        }

        int bonusPoints = getBonusPoints(player);
        player.setScore(dareCard.getWorth() + bonusPoints, 1); // Update score and card quantity
        JOptionPane.showMessageDialog(null, player.getPlayerName() + " completed the dare! Earned points: " + (dareCard.getWorth() + bonusPoints));
    }

    private int getBonusPoints(Player player) {
        // Implement your logic for bonus points here
        return 0; // Example: return a fixed value or calculate based on conditions
    }

    private void declareWinner() {
        Player winner = null;
        for (Player player : players) {
            if (player.isActive()) {
                if (winner == null || player.getCardQty() > winner.getCardQty()) {
                    winner = player;
                }
            }
        }

        if (winner != null) {
            JOptionPane.showMessageDialog(null, "The winner is " + winner.getPlayerName() + " with " + winner.getCardQty() + " cards!");
        } else {
            JOptionPane.showMessageDialog(null, "All players have been eliminated.");
        }
    }

    private int getActivePlayersCount() {
        int count = 0;
        for (Player player : players) {
            if (player.isActive()) {
                count++;
            }
        }
        return count;
    }

    private DareCard getRandomDareCard() {
        Random random = new Random();
        return dareCards.get(random.nextInt(dareCards.size()));
    }
}