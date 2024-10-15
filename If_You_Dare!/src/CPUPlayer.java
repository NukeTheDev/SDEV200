import java.util.Random;

public class CPUPlayer extends Player 
{
    private static final String[] CPU_NAMES = {"Wolf(CPU)", "Skip(CPU)", "Rob(CPU)", "Jax(CPU)", "King(CPU)", "Sam(CPU)", "Twist(CPU)", "Viola(CPU)"};

    public CPUPlayer()
    {
        super(generateRandomName());
    }
    private static String generateRandomName()
    {
        Random random = new Random();
        return CPU_NAMES[random.nextInt(CPU_NAMES.length)];
    }
    @Override
    public boolean isCPU()
    {
        return true;
    }
}