public abstract class Player 
{
    private final String playerName;
    private int cardQty;
    private int score;
    private boolean active;

    public Player(String playerName) 
    {
        this.playerName = playerName;
        this.cardQty = 0;
        this.score = 0;
        this.active = true;
    }

    public String getPlayerName() 
    {
        return playerName;
    }

    public int getScore() 
    {
        return score;
    }

    public int getCardQty() 
    {
        return cardQty;
    }

    public void setCardQty(int qty) 
    {
        this.cardQty = qty;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }

    public boolean isActive() 
    {
        return active;
    }

    public void eliminate() 
    {
        this.active = false;
    }
    public abstract boolean isCPU();
}