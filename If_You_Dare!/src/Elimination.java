/* Brief explanation of game mode objective
 * Elimination: Players are eliminated if they fail dares or lose all their points. 
 * The last player standing wins the game.
 */

import java.util.List;
import javax.swing.JOptionPane;

public class Elimination extends GameMode 
{
    public Elimination(Difficulty difficulty, List<Player> players, List<DareCard> dareCards) 
    {
        super(difficulty, players, dareCards);
        initializePlayerPoints();
    }

    private void initializePlayerPoints() {
        for (Player player : players) {
            switch (difficulty) {
                case EASY -> player.setScore(30);
                case MEDIUM -> player.setScore(20);
                case HARD -> player.setScore(10);
            }
        }
    }

    @Override
    public void playRound() {
        for (Player player : players) 
        {
            if (player.isActive()) 
            {
                playTurn(player);
                // If a player fails a dare or loses all points, eliminate them
                if (player.getScore() <= 0) {
                    player.eliminate();
                }

            if (!gameActive)
                break;
            }
        }
    }

    @Override
    protected void checkGameState() 
    {
        checkForLastPlayer();
    }

    @Override
    protected void announceWinner()
    {
        JOptionPane.showMessageDialog(null, "Congratulations! " + winner.getPlayerName() + ", you are the last player left standing!");
    }
}