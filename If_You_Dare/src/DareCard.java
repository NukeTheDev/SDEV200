public class DareCard {
    private String question;
    private int timeLimit;
    private String difficulty;
    private String category;
    private int worth;

    public DareCard(String question, int timeLimit, String difficulty, String category, int worth) {
        this.question = question;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.category = category;
        this.worth = worth;
    }

    public String getQuestion() {
        return question;
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
}