public class DareCard {
    private String question;
    private int timeLimit;
    private String difficulty;
    private String category;
    private int worth;
    private String answer;

    public DareCard(String question, int timeLimit, String difficulty, String category, int worth, String answer) {
        this.question = question;
        this.timeLimit = timeLimit;
        this.difficulty = difficulty;
        this.category = category;
        this.worth = worth;
        this.answer = answer;
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

    public String getAnswer() {
        return answer;
    }
}