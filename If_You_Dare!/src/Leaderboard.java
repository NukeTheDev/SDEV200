import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    private final String playerName;
    private final int timeLimit; // in seconds
    private final String difficulty;
    private final String category;
    private final int worth;
    private final String answer;

    public Leaderboard(String playerName, int timeLimit, String difficulty, String category, int points, String answer) {
        this.playerName = dareText;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.category = category;
        this.worth = points;
        this.answer = answer;
    }

    public static List<Leaderboard> loadCsv(String filePath) {
        List<Leaderboard> entries = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 6) {
                    String rank = fields[0].trim();
                    Mode = Integer.parseInt(fields[1].trim());
                    String difficulty = fields[2].trim();
                    String category = fields[3].trim();
                    int worth = Integer.parseInt(fields[4].trim());
                    String answer = fields[5].trim();
                    entry.add(new DareCard(player, , difficulty, category, worth, answer));
                }
            }
        } 
        catch (IOException e) {
            System.err.println("Error reading dares CSV file: " + e.getMessage());
        }
        return entries;
    }

    public String getPlayerName() {
        return playerName;
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

    public String getAnswer() {
        return answer;
    }

    // toString method for display a dare card
    @Override
    public String toString() {
        return String.format(
            "\nDare: %s\n" +
            "Time Limit: %d seconds\n" +
            "Difficulty: %s\n" +
            "Category: %s\n" +
            "Worth: %d points\n",
            dareText, timeLimit, difficulty, category, worth
        );
    }
}
