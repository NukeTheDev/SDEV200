import javax.swing.JOptionPane;

public class Tutorial 
{
    public void playTutorial(Mode mode)
    {
        // Define instructions for each game mode
        String[] classicModeInstructions = {
            "Classic Mode:",
            "1. Players take turns drawing dare cards.",
            "2. If you refuse a dare, you lose half the points it’s worth.",
            "3. If you accept the dare but fail, you lose the full points.",
            "4. First player to reach the point threshold wins."
        };

        String[] eliminationModeInstructions = {
            "Elimination Mode:",
            "1. Players take turns drawing dare cards.",
            "2. Refusing a dare loses you half the points.",
            "3. Failing a dare costs you all the points it’s worth.",
            "4. Game ends when one player reaches 0 points and is eliminated."
        };

        String[] timeAttackModeInstructions = {
            "Time Attack Mode:",
            "1. Complete as many dares as possible within the time limit.",
            "2. Each completed dare adds points to your score.",
            "3. Refusing or failing a dare subtracts points from your score.",
            "4. The player with the most points and dares when the timer runs out wins."
        };

        // Show beginning tutorial
        showUserTutorial();
        
        // Show the instructions for each game mode
        switch(mode)
        {
            case CLASSIC -> showGameInstructions("Classic Mode", classicModeInstructions);
            case ELIMINATION -> showGameInstructions("Elimination Mode", eliminationModeInstructions);
            case TIME_ATTACK -> showGameInstructions("Time Attack Mode", timeAttackModeInstructions);
        }
    }


    // Method to show instructions for each game mode
    private static void showGameInstructions(String mode, String[] instructions) {
        StringBuilder message = new StringBuilder();
        for (String instruction : instructions) {
            message.append(instruction).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), mode + " Instructions", JOptionPane.INFORMATION_MESSAGE);
    }

    // Method to show a beginner tutorial
    private static void showUserTutorial() {
        // Split the tutorial into multiple JOptionPane boxes with 4-5 lines each for readability
        String[] tutorial1 = {
            "Welcome to If You Dare!",
            "Here's how to play:",
            "Each player draws a dare card on their turn.",
            "You can either accept or refuse the dare."
        };
        
        String[] tutorial2 = {
            "If you refuse, you lose half the points the dare is worth.",
            "If you accept and fail, you lose all the points.",
            "You may be offered a chance to forfeit after refusal.",
            "The game continues until a winner is declared."
        };
        
        String[] tutorial3 = {
            "The goal varies depending on the game mode.",
            "For more details, select 'Rules' from the main menu.",
            "There you can view a full guide for each mode.",
            "Have fun, and play fair!"
        };

        // Display the beginner tutorial in separate message boxes
        showMessageDialog(tutorial1, "Beginner Tutorial - Part 1");
        showMessageDialog(tutorial2, "Beginner Tutorial - Part 2");
        showMessageDialog(tutorial3, "Beginner Tutorial - Part 3");
    }

    // Helper method to show a message dialog for parts of the tutorial
    private static void showMessageDialog(String[] messages, String title) {
        StringBuilder message = new StringBuilder();
        for (String line : messages) {
            message.append(line).append("\n");
        }
        JOptionPane.showMessageDialog(null, message.toString(), title, JOptionPane.INFORMATION_MESSAGE);
    }
}
