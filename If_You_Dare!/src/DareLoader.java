import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DareLoader {
    private List<DareCard> dareCards;
    private Random random;

    public DareLoader() {
        this.dareCards = new ArrayList<>();
        this.random = new Random();
        loadDares("/workspace/If_You_Dare!/src/dares.txt"); // Specify the path to your dares file
    }

    private void loadDares(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 6) {
                    String question = parts[0];
                    int timeLimit = Integer.parseInt(parts[1]);
                    String difficulty = parts[2];
                    String category = parts[3];
                    int worth = Integer.parseInt(parts[4]);
                    String answer = parts[5];
                    dareCards.add(new DareCard(question, timeLimit, difficulty, category, worth, answer));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DareCard getRandomDare() {
        if (dareCards.isEmpty()) {
            return null;
        }
        int index = random.nextInt(dareCards.size());
        return dareCards.get(index);
    }
}