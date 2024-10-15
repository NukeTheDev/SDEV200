// Brief explanation of game mode objective
// Elimination: Players are eliminated if they fail dares or lose all their points. 
// The last player standing wins the game.

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
                case EASY -> {
                    player.setScore(50);
                }
                case MEDIUM -> {
                    player.setScore(30);
                }
                case HARD -> {
                    player.setScore(20);
                }
            }
        }
    }

    @Override
    public void playRound() {
        for (Player player : players) {
            if (player.isActive()) {
                playTurn(player);
            }
            if (!gameActive) {
                break;
            }
        }
    }

    @Override
    protected void checkGameState() {
        int activePlayers = 0;
        for (Player player : players) {
            if (player.getScore() > 0) {
                activePlayers++;
            }
        }

        if (activePlayers <= 1) {
            gameActive = false;
            setWinner(players.get(0)); // last player left standing

        }
    }

    @Override
    protected void announceWinner()
    {
        JOptionPane.showMessageDialog(null, "Congratulations!" + winner.getPlayerName() + "you are the last player left standing.");
    }
}