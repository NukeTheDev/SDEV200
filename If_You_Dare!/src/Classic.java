// Brief explanation of game mode objective
// Classic: Players take turns completing dares to accumulate points. The player 
// with the most points at the end of the game wins.

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
            {
                break;
            }
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
    }

    @Override
    protected void announceWinner()
    {
        JOptionPane.showMessageDialog(null, winner.getPlayerName() + "Has reached" + pointThreshold + "points!");
    }
}