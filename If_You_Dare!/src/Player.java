import javax.swing.JOptionPane;

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

    public void setActive(boolean state) 
    {
        this.active = state;
    }

    // Forfeiting logic for all players across all game modes.
    public void eliminate() 
    {
        JOptionPane.showMessageDialog(null, this.getPlayerName() + " has been eliminated!");
        setActive(false);
    }

    // Elimination logic for all players across all game modes.
    public void forfeit() 
    {
        JOptionPane.showMessageDialog(null, this.getPlayerName() + " has chosen to forfeit!");
        setActive(false);
    }

    public boolean isActive() 
    {
        return active;
    }
    
    public abstract boolean isCPU();
}