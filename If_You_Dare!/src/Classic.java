import java.util.List;

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
            case EASY -> pointThreshold = 100;
            case MEDIUM -> pointThreshold = 150;
            case HARD -> pointThreshold = 200;
        }
    }

    @Override
    public void playRound() {
        for (Player player : players) {
            playTurn(player);
            if (!gameActive) {
                break;
            }
        }
    }
    
    @Override
    protected void checkGameState() 
    {
        for (Player player : players) {
            if (player.getScore() >= pointThreshold) {
                gameActive = false;
            }
        }
    }
    @Override
    protected Player determineWinner()
    {
        for (Player player : players)
            if (player.getScore() >= pointThreshold)
                winner = player;
        return winner;
    }
}