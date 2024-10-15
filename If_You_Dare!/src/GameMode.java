// Brief explanation of game mode objective

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

public abstract class GameMode {
    protected Difficulty difficulty;
    protected List<Player> players;
    protected List<DareCard> dareCards;
    protected boolean gameActive = true;
    protected Player winner; // The winner of the game

    public GameMode(Difficulty difficulty, List<Player> players, List<DareCard> dareCards) {
        this.difficulty = difficulty;
        this.players = players;
        this.dareCards = dareCards;
    }

    protected boolean isGameOver()
    {
        return !gameActive;
    }

    protected void setWinner(Player player)
    {
        winner = player;
        System.out.println("Winner: " + winner);
    }
    
    protected Player getWinner()
    {
        return winner;
    }

    protected abstract void announceWinner();

    public abstract void playRound();

    public void playTurn(Player currentPlayer) 
    {
        if (!currentPlayer.isActive()) return; // Skip if player is inactive

        // Check if the game is still active before proceeding
        if (isGameOver()) return;

        // Display a message for the current player including CPU player's
        String message = currentPlayer.getPlayerName() + "'s turn";
        JOptionPane.showMessageDialog(null, message, "Player Prompt", JOptionPane.INFORMATION_MESSAGE);

        DareCard dare = getRandomDare();       
        processDare(currentPlayer, dare); // show the dare to the currentPlayer
        // Check if the game should continue after the turn
        checkGameState(); 
    }

    private void processDare(Player player, DareCard dareCard) 
    {
        if (player.isCPU()) {
            // CPU player's turn is handled by cpuTurn()
            cpuTurn(player, dareCard);
        } else {
            int response = JOptionPane.showConfirmDialog(
                null,
                player.getPlayerName() + ", your dare is: " + dareCard.toString() + "\nDo you accept the dare?",
                "Dare Challenge",
                JOptionPane.YES_NO_OPTION);

                if (response == JOptionPane.YES_OPTION) 
                {
                    long startTime = System.currentTimeMillis(); // Start the timer
                    executeDareChallenge(player, dareCard, startTime);
                } 
                else if (response == JOptionPane.NO_OPTION) 
                {
                    int forfeitResponse = JOptionPane.showConfirmDialog(null,
                            "Do you want to forfeit the game?",
                            "Forfeit", JOptionPane.YES_NO_OPTION);

                    if (forfeitResponse == JOptionPane.YES_OPTION) 
                        forfeit(player);
                    else if (response == JOptionPane.NO_OPTION)
                        handleDareException(player, dareCard, "refused");
                }
        }
    }

    private void cpuTurn(Player cpuPlayer, DareCard dareCard) {
        // Simulate CPU "thinking" time
        try {
            Thread.sleep(1000); // 1 second delay to mimic thinking time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
        }
        // Stores the current CPU's player name as a String
        String cpuPlayerName = cpuPlayer.getPlayerName();
        // Decision-making logic:
        double randomDecision = Math.random();
    
        // 10% chance to forfeit the game
        if (randomDecision < 0.1) {
            System.out.println(cpuPlayerName + " has chosen to forfeit the game.");
            forfeit(cpuPlayer);
        }
    
        // 70% chance to accept the dare, 20% chance to refuse
        if (randomDecision < 0.8) {
            System.out.println(cpuPlayerName + " has accepted the dare!");
    
            // Simulate the CPU trying to complete the dare
            long simulatedTimeTaken = (long) (Math.random() * (dareCard.getTimeLimit() + 1)); // Random time between 0 and time limit
    
            // Simulate whether CPU completes the dare successfully or not (let's assume 80% success rate)
            if (Math.random() < 0.8) {
                // CPU successfully completes the dare
                System.out.println(cpuPlayerName + " successfully completed the dare!");
                handleDareCompletion(cpuPlayer, dareCard, simulatedTimeTaken);
            } else {
                // CPU fails the dare
                System.out.println(cpuPlayerName + " failed the dare.");
                handleDareException(cpuPlayer, dareCard, "failed");
            }
        } else {
            // CPU refuses the dare
            System.out.println(cpuPlayerName + " has refused the dare.");
            handleDareException(cpuPlayer, dareCard, "refused");
        }
    }
    

    private void executeDareChallenge(Player player, DareCard dareCard, long startTime) {
        int timeLimit = dareCard.getTimeLimit();
    
        // Create a timer for the countdown
        Timer countdownTimer = new Timer(1000, new ActionListener() {
            private int timeLeft = timeLimit;
    
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft <= 0) {
                    ((Timer) e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Time's up! " + player.getPlayerName() + " has failed the dare!" +
                            "\nNew score: " + player.getScore());
                    
                    // Simulate an incorrect answer
                    handleDareException(player, dareCard, "failed");
                    // Close the input dialog if time is up
                    ((Timer) e.getSource()).stop(); // Ensure timer stops
                }
            }
        });
        countdownTimer.start();
    
        // Show the input dialog for the player's answer
        String answer = JOptionPane.showInputDialog(null, 
            player.getPlayerName() + ", your dare is: " + dareCard.getdareText() +
            "\nYou have " + timeLimit + " seconds. Please enter your answer:", 
            "Dare Challenge", JOptionPane.QUESTION_MESSAGE);
    
        // Stop the timer if dialog is closed or input is received
        countdownTimer.stop();

        // Check the answer
        if (answer == null || answer.trim().isEmpty() || answer.equals("failure")) {
            handleDareException(player, dareCard, "failed");
        } else if (dareCard.isCorrect(answer)) {
            long timeTaken = (System.currentTimeMillis() - startTime) / 1000; // Time in seconds
            handleDareCompletion(player, dareCard, timeTaken);
        } else {
            handleDareException(player, dareCard, "failed");
        }
    }    
    

    // Handle dare completion with time tracking
    private void handleDareCompletion(Player player, DareCard dareCard, long timeTaken) {
        int earnedPoints = 0;

        // Determine points based on time taken
        if (timeTaken <= 10) 
        {
            earnedPoints = dareCard.getWorth() + 10; // Fast completion bonus
            JOptionPane.showMessageDialog(null, "Slick trick! " + player.getPlayerName() + 
            "\n earned 10 bonus points for a fast completion time.");
        } 
        else if (timeTaken <= 20)
            earnedPoints = dareCard.getWorth(); // Full total for answering in average time
        else 
        {
            earnedPoints = dareCard.getWorth() - 10; // Slower completion penalty
            JOptionPane.showMessageDialog(null, "Speed it up next time! -10 points");
        }
        String playerName = player.getPlayerName();
        player.setCardQty(player.getCardQty() + 1); // player collects a dare card
        player.setScore(player.getScore() + earnedPoints);
        
        JOptionPane.showMessageDialog(
            null, "Nice work! " + playerName + " completed their dare in " + timeTaken + " seconds.\n" +
            playerName + " has earned " + earnedPoints + " points.\n" +
            playerName + "'s new point total is: " + player.getScore());
    }

    private void handleDareException(Player player, DareCard dareCard, String reason) {
        int pointsDeducted = 0;
        if ("refused".equals(reason)) 
            pointsDeducted = dareCard.getWorth() / 2; // Lose half the points for refusal
        else if ("failed".equals(reason)) 
            pointsDeducted = dareCard.getWorth(); // Lose full points for failure

        player.setScore(player.getScore() - pointsDeducted); // Penalty for dare refusal or failure
        JOptionPane.showMessageDialog(null, player.getPlayerName() + " " + reason + " the dare! Points deducted: " + pointsDeducted + "\nNew score: " + player.getScore());
    }

    private DareCard getRandomDare() {
        int randomIndex = (int) (Math.random() * dareCards.size());
        return dareCards.get(randomIndex);
    }

    private void forfeit(Player player) {
        JOptionPane.showMessageDialog(null, player.getPlayerName() + " has forfeited the game!");
        players.remove(player);

        // Continue the game with the remaining players
        playRound();
    }

    protected abstract void checkGameState();
}
