import java.util.Random;

public class CPUPlayer extends Player {
    private static final String[] CPU_NAMES = {"Bot1", "Bot2", "Bot3", "Bot4"};

    public CPUPlayer() {
        super(generateRandomName());
    }

    private static String generateRandomName() {
        Random random = new Random();
        return CPU_NAMES[random.nextInt(CPU_NAMES.length)];
    }
}