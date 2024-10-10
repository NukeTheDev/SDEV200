import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DareGame {
    private List<Player> players; // Array of player objects
    private int currentPlayerIndex;
    private int gameMode; // 1 = Classic, 2 = Elimination, 3 = Time Attack
    private int difficulty; // 1 = Easy, 2 = Medium, 3 = Hard
    private int startingPoints; // The amount each player begins the game with
    private BonusHandler bonusHandler; // Future feature
    private DareLoader dareLoader;
    private boolean gameActive;
    private int timeAttackLimit; // Time for Time Attack mode
    private int pointThreshold; // Threshold for Classic mode

    public DareGame() {
        players = new ArrayList<>();
        currentPlayerIndex = 0;
        bonusHandler = new BonusHandler(); dareLoader = new DareLoader();
        gameActive = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DareGame game = new DareGame(); game.startGame();
        });
    }

    private void startGame() {
        selectGameMode(); selectGameDifficulty(); registerPlayers();

        if (gameMode == 3) { // Time Attack
            selectTimeAttackLimit(); startTimeAttackTimer();
        } 
        while (gameActive) {
            playTurn();
        }
        announceWinner();
    }

    private void selectGameMode() {
        String[] modes = {"Classic", "Elimination", "Time Attack"};
        String selectedMode = (String) JOptionPane.showInputDialog(null, "Choose Game Mode:",
                "Game Mode Selection", JOptionPane.QUESTION_MESSAGE, null, modes, modes[0]);

        if (selectedMode != null) {
            gameMode = switch (selectedMode) {
                case "Classic" -> 1;
                case "Elimination" -> 2;
                case "Time Attack" -> 3;
                default -> 1;
            };
        }
    }

    private void selectGameDifficulty() {
        String[] difficulties = {"Easy", "Medium", "Hard"};
        String selectedDifficulty = (String) JOptionPane.showInputDialog(null, "Choose Game Difficulty:",
                "Difficulty Selection", JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[0]);

        if (selectedDifficulty != null) 
        {
            difficulty = switch (selectedDifficulty) {
                case "Easy" -> 1;
                case "Medium" -> 2;
                case "Hard" -> 3;
                default -> 2;
            };

            if (gameMode == 1) 
            { // Classic mode
                startingPoints = 0;
                pointThreshold = switch (difficulty) {
                    case 1 -> 100; // Easy
                    case 2 -> 150; // Medium
                    case 3 -> 200; // Hard
                    default -> 150;
                };
            }
            else if (gameMode == 2) // Elimination mode
            {
                startingPoints = switch (difficulty) {
                    case 1 -> 50; // Easy
                    case 2 -> 25; // Medium
                    case 3 -> 10; // Hard
                    default -> 50;
                };
            }
        }
    }

    private void selectTimeAttackLimit() {
        String[] times = {"120 seconds (2 mins)", "300 seconds (5 mins)", "600 seconds (10 mins)"};
        String selectedTime = (String) JOptionPane.showInputDialog(null, "Choose Time Limit for Time Attack:",
                "Time Attack Selection", JOptionPane.QUESTION_MESSAGE, null, times, times[0]);

        if (selectedTime != null) {
            timeAttackLimit = switch (selectedTime) {
                case "120 seconds (2 mins)" -> 120;
                case "300 seconds (5 mins)" -> 300;
                case "600 seconds (10 mins)" -> 600;
                default -> 300;
            };
        }
    }

    private void startTimeAttackTimer() {
        Timer timeAttackTimer = new Timer(1000, new ActionListener() {
            private int timeLeft = timeAttackLimit;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft <= 0) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Time's up! Time Attack mode has ended.");
                    gameActive = false;
                    announceWinner();
                }
            }
        });
        timeAttackTimer.start();
    }

    private void registerPlayers() {
        String[] playerOptions = {"1", "2", "3", "4"};
        String numPlayersStr = (String) JOptionPane.showInputDialog(null,
                "Select number of local players:",
                "Player Registration",
                JOptionPane.QUESTION_MESSAGE,
                null,
                playerOptions,
                playerOptions[0]);

        int numPlayers = 0;
        if (numPlayersStr != null) {
            numPlayers = Integer.parseInt(numPlayersStr);
            for (int i = 0; i < numPlayers; i++) {
                String playerName = JOptionPane.showInputDialog("Enter name for Player " + (i + 1) + ":");
                players.add(new Player(playerName, startingPoints));
            }
        }

        int maxCPUPlayers = 4 - numPlayers;
        String numCPUPlayersStr = (String) JOptionPane.showInputDialog(null,
                "Select number of CPU players (up to " + maxCPUPlayers + "):",
                "CPU Player Registration",
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"0", "1", "2", "3"},
                "0");

        if (numCPUPlayersStr != null) {
            int numCPUPlayers = Integer.parseInt(numCPUPlayersStr);
            for (int i = 0; i < numCPUPlayers; i++) {
                players.add(new CPUPlayer(startingPoints));
            }
        }
    }

    private void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        if (!currentPlayer.isActive()) {
            nextPlayer();
            return;
        }

        String message = currentPlayer.getPlayerName() + "'s turn";
        JOptionPane.showMessageDialog(null, message, "Dare Time", JOptionPane.INFORMATION_MESSAGE);

        DareCard dareCard = dareLoader.getRandomDare();
        if (dareCard != null) {
            processDare(currentPlayer, dareCard);
        }

        checkGameStatus();
        nextPlayer();
    }

    private void processDare(Player player, DareCard dareCard) {
        String dareMessage = String.format("\nTime Limit: %d seconds\nDifficulty: %s\nCategory: %s\nWorth: %d points\n\n",
                dareCard.getTimeLimit(), dareCard.getDifficulty(), dareCard.getCategory(), dareCard.getWorth());

        int acceptDare = JOptionPane.showConfirmDialog(null, dareMessage + "Do you take the dare?", "Dare Card", JOptionPane.YES_NO_OPTION);

        if (acceptDare == JOptionPane.YES_OPTION) {
            long startTime = System.currentTimeMillis(); // Start the timer
            executeDareChallenge(player, dareCard, startTime);
        } 
        else 
        {
            handleDareException(player, dareCard, "refused");
        }
    }

    private void executeDareChallenge(Player player, DareCard dareCard, long startTime) {
        int timeLimit = dareCard.getTimeLimit();

        Timer countdownTimer = new Timer(1000, new ActionListener() {
            private int timeLeft = timeLimit;

            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft <= 0) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Time's up! " + player.getPlayerName() + " has failed the dare!" +
                            "\nNew score: " + player.getScore());
                    handleDareException(player, dareCard, "failed");
                }
            }
        });
        countdownTimer.start();

        String answer = JOptionPane.showInputDialog(null,
                dareCard.getQuestion(),
                player.getPlayerName() + "'s Dare",
                JOptionPane.QUESTION_MESSAGE);
        countdownTimer.stop();

        if (answer != null && !answer.trim().isEmpty() && answer.equalsIgnoreCase(dareCard.getAnswer())) {
            long timeTaken = (System.currentTimeMillis() - startTime) / 1000; // Time in seconds
            handleDareCompletion(player, dareCard, timeTaken);
        } 
        else 
        {
            handleDareException(player, dareCard, "failed");
        }
    }

    // Time Attack: Handle completion with time tracking
    private void handleDareCompletion(Player player, DareCard dareCard, long timeTaken) {
        int earnedPoints = 0;

        // Determine points based on time taken
        if (timeTaken <= 10) 
        {
            earnedPoints = dareCard.getWorth() + 10; // Fast completion bonus
            JOptionPane.showMessageDialog(null, "Slick trick! You earned 10 bonus points");
        } 
        else if (timeTaken <= 20) 
        {
            earnedPoints = dareCard.getWorth(); // Full total for answering in average time
        } 
        else 
        {
            earnedPoints = dareCard.getWorth() - 10; // Slower completion penalty
            JOptionPane.showMessageDialog(null, "Speed it next time! -10 points");
        }

        player.setScore(player.getScore() + earnedPoints);
        JOptionPane.showMessageDialog(null,
                "Well done! You completed the dare in " + timeTaken + " seconds. You earned " + earnedPoints + " points.\nNew score: " + player.getScore());
    }

    private void handleDareException(Player player, DareCard dareCard, String reason) {
        if (gameMode == 1 || gameMode == 2) // Executes for every mode except for Time Attack
        { 
            int pointsDeducted = 0;
            if (reason == "refused")
            {
                pointsDeducted = dareCard.getWorth() / 2; // Lose half the points for refusal
            }
            else if (reason == "failed")
            {
                pointsDeducted = dareCard.getWorth(); // Lose the full amount of points for failure
            }
            player.setScore(player.getScore() - pointsDeducted); // Penalty for dare refusal or failure

            // Check for elimination
            if (player.getScore() <= 0)
            {
                player.setScore(0); // Ensures score does not go negative in both classic and elimination mode
                if(gameMode == 2)
                {
                    player.eliminate();
                    JOptionPane.showMessageDialog(null, player.getPlayerName() + " has run out of points and has been eliminated!");
                } 
            }

            JOptionPane.showMessageDialog(null, player.getPlayerName() + " " + reason + " the dare! \n Points deducted: " + pointsDeducted + "\nNew score: " + player.getScore());
    
            // Check if game is over
            checkGameStatus();
        }
    }

    private void checkGameStatus() {
        if (gameMode == 1) { // Classic Mode
            for (Player player : players) {
                if (player.getScore() >= pointThreshold) {
                    gameActive = false;
                    break;
                }
            }
        } else if (gameMode == 2) { // Elimination Mode
            int activePlayers = 0;
            for (Player player : players) {
                if (player.getScore() > 0) {
                    activePlayers++;
                }
            }
            gameActive = activePlayers > 1;
        }
    }

    private void nextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }

    private void announceWinner() {
        Player winner = players.get(0);
        for (Player player : players) {
            if (player.getScore() > winner.getScore()) {
                winner = player;
            }
        }
        JOptionPane.showMessageDialog(null, winner.getPlayerName() + " is the winner!");
    }
}