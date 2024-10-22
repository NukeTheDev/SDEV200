/* Classic: Players take turns completing dares to accumulate points. 
 * The player that reaches the points threshold for that difficulty first, wins.
 */

import java.util.List;
import javax.swing.JOptionPane;

public class Classic extends GameMode 
{
    private int pointThreshold;
    public Classic(Difficulty difficulty, List<Player> players, List<DareCard> dareCards) 
    {
        super(difficulty, players, dareCards);
        setPointsThreshold();
    }

    private void setPointsThreshold() 
    {
        switch(difficulty) 
        {
            case EASY -> pointThreshold = 50;
            case MEDIUM -> pointThreshold = 100;
            case HARD -> pointThreshold = 150;
        }
    }

    @Override
    public void playRound() 
    {
        for (Player player : players) 
        {
            playTurn(player);
            if (!gameActive) 
                break;
        }
    }
    
    @Override
    protected void checkGameState() 
    {
        for (Player player : players) 
        {
            if (player.getScore() >= pointThreshold) 
            {
                gameActive = false;
                setWinner(player); // set the winner
            }
        }
        // See if there is only one player left in the game.
        // If there is, set them as the winner
        checkForLastPlayer();
    }

    @Override
    protected void announceWinner()
    {
        JOptionPane.showMessageDialog(null, "Congratulations! " + winner.getPlayerName() + 
        "\n You win!");
    }
}