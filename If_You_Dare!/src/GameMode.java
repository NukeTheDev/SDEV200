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
        if (!currentPlayer.isActive()) {
            return; // Skip if player is inactive
        }
        if (currentPlayer.isCPU()) 
        {
            JOptionPane.showMessageDialog(null, "CPU is taking their turn. Please wait...");
            cpuTurn();
            return; // Proceed to next local player if CPU
        } 
        else if(currentPlayer.isActive())
        {
            // Display a message for the current player
            String message = currentPlayer.getPlayerName() + "'s turn";
            JOptionPane.showMessageDialog(null, message, "Your Turn", JOptionPane.INFORMATION_MESSAGE);

            DareCard dare = getRandomDare();       

            processDare(currentPlayer, dare); // show the dare to the currentPlayer
        }
        // Check if the game should continue after the turn
        checkGameState(); 
    }

    private void processDare(Player player, DareCard dareCard) 
    {
        int response = JOptionPane.showConfirmDialog(
                        null,
                        player.getPlayerName() + ", your dare is: " + dareCard.toString() + "\nDo you accept the dare?",
                        "Dare Challenge",
                        JOptionPane.YES_NO_OPTION
                );

                if (response == JOptionPane.YES_OPTION) {
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
                dareCard.getdareText(),
                player.getPlayerName() + "'s Dare",
                JOptionPane.QUESTION_MESSAGE);
        countdownTimer.stop();

        if (answer != null && !answer.trim().isEmpty() && dareCard.isCorrect(answer)) {
            long timeTaken = (System.currentTimeMillis() - startTime) / 1000; // Time in seconds
            handleDareCompletion(player, dareCard, timeTaken);
        } 
        else {
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
        else if (timeTaken <= 20) {
            earnedPoints = dareCard.getWorth(); // Full total for answering in average time
        } 
        else 
        {
            earnedPoints = dareCard.getWorth() - 10; // Slower completion penalty
            JOptionPane.showMessageDialog(null, "Speed it up next time! -10 points");
        }

        player.setCardQty(player.getCardQty() + 1); // player collects a dare card
        player.setScore(player.getScore() + earnedPoints);
        JOptionPane.showMessageDialog(null,
                "Well done! You completed the dare in " + timeTaken + " seconds. You earned " + earnedPoints + " points.\nNew score: " + player.getScore());
    }

    private void handleDareException(Player player, DareCard dareCard, String reason) {
        int pointsDeducted = 0;
        if ("refused".equals(reason)) 
        {
            pointsDeducted = dareCard.getWorth() / 2; // Lose half the points for refusal
        } 
        else if ("failed".equals(reason)) 
        {
            pointsDeducted = dareCard.getWorth(); // Lose full points for failure
        }

        player.setScore(player.getScore() - pointsDeducted); // Penalty for dare refusal or failure

        JOptionPane.showMessageDialog(null, player.getPlayerName() + " " + reason + " the dare! Points deducted: " + pointsDeducted + "\nNew score: " + player.getScore());
    }

    private DareCard getRandomDare() {
        int randomIndex = (int) (Math.random() * dareCards.size());
        return dareCards.get(randomIndex);
    }

    protected void cpuTurn() {
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

    protected abstract void checkGameState();
}
