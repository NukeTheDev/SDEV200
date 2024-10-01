import java.util.ArrayList;
import java.util.List;

public class Player 
{
    private String playerName; 
    private int cardQty; private int points; 
    private boolean isActive;
    private List<DareCard> collectedDareCards; // Collection to store collected dare cards

    public Player(String name, int pts)
    {
        this(name, 0, pts, true);
        this.collectedDareCards = new ArrayList<>(); // Initialize the list  
    }

    public Player(String name, int qty, int pts, boolean state) 
    {
        this.playerName = name; this.cardQty = qty;  
        this.points = pts; this.isActive = state;
    }

    // Getter for player name
    public String getPlayerName() 
    {
        return playerName;  
    }

    // Getter for points (score)
    public int getScore() {
        return points;
    }

    // Setter for player name (optional, depending on your need)
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    // Setter for points (score)
    public void setScore(int points) {
        this.points = points;
    }

    // Getter for card quantity
    public int getCardQty() {
        return cardQty;
    }

    // Setter for card quantity
    public void setCardQty(int cardQty) {
        this.cardQty = cardQty;
    }

    // Combined setter for points and card quantity
    public void setScore(int pts, int qty) {
        this.points = pts;
        this.cardQty = qty;
    }

    // Getter for player's active state
    public boolean isActive() { 
        return isActive; 
    }

    // Setter for player's active state
    public void setActive(boolean active) {
        this.isActive = active;
    }

    // Method to eliminate a player
    public void eliminate() { 
        this.isActive = false;  
    }

    // Method to add a collected dare card
    public void collectDareCard(DareCard dareCard) {
        collectedDareCards.add(dareCard);
    }

    // Getter for collected dare cards
    public List<DareCard> getCollectedDareCards() 
    {
        return collectedDareCards;
    }
}