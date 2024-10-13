import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DareCard {
    private final String dareText;
    private final int timeLimit; // in seconds
    private final String difficulty;
    private final String category;
    private final int worth;
    private final String answer;

    public DareCard(String dareText, int timeLimit, String difficulty, String category, int points, String answer) {
        this.dareText = dareText;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.category = category;
        this.worth = points;
        this.answer = answer;
    }

    public static List<DareCard> loadCsv(String filePath) {
        List<DareCard> dareCards = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    String dareText = fields[0].trim();
                    int timeLimit = Integer.parseInt(fields[1].trim());
                    String difficulty = fields[2].trim();
                    String category = fields[3].trim();
                    int worth = Integer.parseInt(fields[4].trim());
                    String answer = fields[5].trim();
                    dareCards.add(new DareCard(dareText, timeLimit, difficulty, category, worth, answer));
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Error reading dares CSV file: " + e.getMessage());
        }
        return dareCards;
    }

    public String getdareText() {
        return dareText;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCategory() {
        return category;
    }

    public int getWorth() {
        return worth;
    }

    public boolean isCorrect(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }

    // toString method for display a dare card
    @Override
    public String toString() {
        return String.format("""
                             
                             Dare: %s
                             Time Limit: %d seconds
                             Difficulty: %s
                             Category: %s
                             Worth: %d points
                             """,
            dareText, timeLimit, difficulty, category, worth
        );
    }
}
