import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameSetup extends JFrame {
    private static final String CSV_PATH = "/workspace/If_You_Dare!/src/dares.csv"; // Path to the CSV file
    private JFrame currentFrame;
    private JFrame gameModeFrame;
    private JFrame chooseDifficultyFrame;
    private JFrame playerSetupFrame;
    private JComboBox<Integer> playerQtyComboBox;
    private JComboBox<Integer> cpuQtyComboBox;
    private Integer[] playerOptions = {2, 3, 4, 5, 6};

    public GameSetup() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showGameModes();
    }

    public void showGameModes() {
        gameModeFrame = new JFrame("Game Setup");
        gameModeFrame.setLayout(new GridLayout(4, 1));

        JLabel gameModeLabel = new JLabel("Select Game Mode: ");
        JButton classicButton = new JButton("Classic");
        JButton eliminationButton = new JButton("Elimination");
        JButton timeAttackButton = new JButton("Time Attack");

        classicButton.addActionListener(e -> showDifficultySelection(Mode.CLASSIC));
        eliminationButton.addActionListener(e -> showDifficultySelection(Mode.ELIMINATION));
        timeAttackButton.addActionListener(e -> showDifficultySelection(Mode.TIME_ATTACK));

        gameModeFrame.add(gameModeLabel);
        gameModeFrame.add(classicButton);
        gameModeFrame.add(eliminationButton);
        gameModeFrame.add(timeAttackButton);

        // Initialize frame
        gameModeFrame.pack();
        gameModeFrame.setLocationRelativeTo(null);
        gameModeFrame.setVisible(true);

    }

    private void showDifficultySelection(Mode gameMode) {
        chooseDifficultyFrame = new JFrame("Choose Difficulty");
        chooseDifficultyFrame.setLayout(new GridLayout(4, 1));

        gameModeFrame.dispose();  // Close the previous frame
        gameModeFrame.setVisible(false);

        JLabel chooseDifficultyLabel = new JLabel("Choose Difficulty: ");
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        easyButton.addActionListener(e -> showPlayerSetup(gameMode, Difficulty.EASY));
        mediumButton.addActionListener(e -> showPlayerSetup(gameMode, Difficulty.MEDIUM));
        hardButton.addActionListener(e -> showPlayerSetup(gameMode, Difficulty.HARD));

        chooseDifficultyFrame.add(chooseDifficultyLabel);
        chooseDifficultyFrame.add(easyButton);
        chooseDifficultyFrame.add(mediumButton);
        chooseDifficultyFrame.add(hardButton);
        
        chooseDifficultyFrame.pack();
        chooseDifficultyFrame.setLocationRelativeTo(null);
        chooseDifficultyFrame.setVisible(true);
    }

    private void showPlayerSetup(Mode gameMode, Difficulty difficulty) {

        playerSetupFrame = new JFrame("Player Setup");
        playerSetupFrame.setLayout(new GridLayout(4, 1));

        chooseDifficultyFrame.dispose();  // Close the previous frame
        chooseDifficultyFrame.setVisible(false); // Close the previous frame

        playerQtyComboBox = new JComboBox<>(playerOptions);
        playerSetupFrame.add(new JLabel("How many local players?"));
        playerSetupFrame.add(playerQtyComboBox);

        cpuQtyComboBox = new JComboBox<>(new Integer[0]);
        playerSetupFrame.add(new JLabel("How many CPUs?"));
        playerSetupFrame.add(cpuQtyComboBox);
        playerQtyComboBox.addActionListener(e -> updateCPUOptions());

        JButton startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(e -> startGame(gameMode, difficulty));

        playerSetupFrame.add(startGameButton);

        playerSetupFrame.pack();
        playerSetupFrame.setLocationRelativeTo(null);
        playerSetupFrame.setVisible(true);
    }

    private void updateCPUOptions() {
        int localPlayerQty = (int) playerQtyComboBox.getSelectedItem();
        int maxCpuPlayers = 6 - localPlayerQty;
        Integer[] cpuOptions = new Integer[maxCpuPlayers + 1];
        for (int i = 0; i <= maxCpuPlayers; i++) {
            cpuOptions[i] = i;
        }
        cpuQtyComboBox.setModel(new DefaultComboBoxModel<>(cpuOptions));
    }

    private void startGame(Mode gameMode, Difficulty difficulty) {

        int localPlayerQty = (int) playerQtyComboBox.getSelectedItem();
        int cpuPlayerQty = (int) cpuQtyComboBox.getSelectedItem();

        playerSetupFrame.dispose();
        playerSetupFrame.setVisible(false);

        List<Player> players = registerPlayers(localPlayerQty, cpuPlayerQty);

        List<DareCard> dareCards = DareCard.loadCsv(CSV_PATH);
        Game game = new Game(gameMode, difficulty, players, dareCards);
        game.playRound();

    }

    private List<Player> registerPlayers(int playerCount, int cpuCount) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) {
            String localPlayerName = JOptionPane.showInputDialog(null, "Enter name for Local Player " + i + ":");
            if (localPlayerName != null && !localPlayerName.trim().isEmpty()) {
                players.add(new LocalPlayer(localPlayerName));
            } else {
                players.add(new LocalPlayer("Player " + i));
            }
        }
        for (int i = 1; i <= cpuCount; i++) {
            players.add(new CPUPlayer());
        }
        return players;
    }
}
