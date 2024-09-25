import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DareLoader {
    private static final String DARE_CARD_FILE = "dare_cards.txt"; // Update with your actual file path

    public static List<DareCard> loadDareCards() {
        List<DareCard> dareCards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DARE_CARD_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) { // Expecting 5 parts now: question, category, difficulty, worth, time limit
                    String question = parts[0].trim();
                    String category = parts[1].trim();
                    String difficulty = parts[2].trim();
                    int worth = Integer.parseInt(parts[3].trim());
                    int timeLimit = Integer.parseInt(parts[4].trim()); // Read time limit
                    dareCards.add(new DareCard(question, timeLimit, category, difficulty, worth)); // Include time limit
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dareCards;
    }
}