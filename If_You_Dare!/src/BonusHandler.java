import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class BonusHandler 
{
    // Check and apply bonuses based on the player's collected cards
    public void checkBonuses(Player player) 
    {
        List<DareCard> collectedCards = player.getCollectedDareCards();
        if (collectedCards.size() >= 3) {
            // Check for 3 dares in a row (any 3)
            awardBonus(player, 2, 5, "3 consecutive dares");
            // Check for 3 dares of the same category
            if (hasThreeOfSameCategory(collectedCards)) {
                awardBonus(player, 5, 9, "3 dares in the same category");
            }
            // Check for 3 dares of the same difficulty
            else if (hasThreeOfSameDifficulty(collectedCards)) {
                awardBonus(player, 10, 15, "3 dares of the same difficulty");
            }
        }
    }
    // Check if the player has 3 dares of the same category
    private boolean hasThreeOfSameCategory(List<DareCard> collectedCards) {
        int funnyCount = 0, grossCount = 0, boldCount = 0;

        for (DareCard card : collectedCards) {
            switch (card.getCategory()) 
            {
                case "funny": funnyCount++; break;
                case "gross": grossCount++; break;
                case "bold": boldCount++; break;
            }
        }

        return funnyCount >= 3 || grossCount >= 3 || boldCount >= 3;
    }
    // Check if the player has 3 dares of the same difficulty
    private boolean hasThreeOfSameDifficulty(List<DareCard> collectedCards) {
        int easyCount = 0, mediumCount = 0, hardCount = 0;
        for (DareCard card : collectedCards) {
            switch (card.getDifficulty()) {
                case "easy": easyCount++; break;
                case "medium": mediumCount++; break;
                case "hard": hardCount++; break;
            }
        }
        return easyCount >= 3 || mediumCount >= 3 || hardCount >= 3;
    }
    // Award bonus points to the player
    private void awardBonus(Player player, int minBonus, int maxBonus, String condition) {
        Random random = new Random();
        int bonusPoints = random.nextInt(maxBonus - minBonus + 1) + minBonus;
        
        player.setScore(player.getScore() + bonusPoints);
        JOptionPane.showMessageDialog(null, player.getPlayerName() + " earned " + bonusPoints + " bonus points for " + condition + "!");
    }
}