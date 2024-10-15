// Brief explanation of game mode objective
// TimeAttack: Players race against a set time limit to complete as many dares as 
// possible. The player with the most points when time runs out wins.

import java.util.List;
import javax.swing.JOptionPane;

public class TimeAttack extends GameMode 
{
    private final int timeLimitInMinutes;
    private int remainingTime;

    public TimeAttack(Difficulty difficulty, List<Player> players, List<DareCard> dareCards, int timeLimit) {
        super(difficulty, players, dareCards);
        timeLimitInMinutes = timeLimit;
        this.remainingTime = timeLimitInMinutes * 60; // remaining time is in seconds form
    }


    @Override
    public void playRound() {
        long startTime = System.currentTimeMillis();
        while ((System.currentTimeMillis() - startTime) < remainingTime * 1000) { 
            for (Player player : players) {
                if (gameActive) {
                    playTurn(player);
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Time is up!");
        gameActive = false; // Time's up, end the game
        setWinner(determineWinner());
    }

    protected Player determineWinner()
    {
        Player winner = players.get(0); // initialize winner

        // Iterate through players to find the one with the highest total of points and card quantity
        for (Player player : players) {
            int cardQty = player.getCardQty();
            int score = player.getCardQty();
            int totalCurrentPlayer = (cardQty + score);
            int totalWinner = winner.getScore() + winner.getCardQty();

            if (totalCurrentPlayer > totalWinner) {
                winner = player; // Update the winner if the current player has a higher total
            }
        }
        return winner; // Return the winning player
    }

    @Override
    protected void checkGameState() {
        // No special check, game ends after time limit
    }
    @Override
    protected void announceWinner()
    {
        // Print out the winning player's details
        System.out.println("Winner: " + winner.getPlayerName() + " with score: " + winner.getScore() + " and card quantity: " + winner.getCardQty());
    }
}
