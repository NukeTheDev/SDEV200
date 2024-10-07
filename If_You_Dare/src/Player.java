public class Player {
    private String playerName;
    private int score;
    private int cardQty;
    private boolean active;

    public Player(String name) {
        this.playerName = name;
        this.score = 0;
        this.cardQty = 0;
        this.active = true;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public int getCardQty() {
        return cardQty;
    }

    public boolean isActive() {
        return active;
    }

    public void eliminate() {
        this.active = false;
    }

    // Set score and card quantity together
    public void setScore(int points, int qty) {
        this.score += points;   // Increment the score by the provided points
        this.cardQty += qty;    // Increment the card quantity by the provided qty
    }
}