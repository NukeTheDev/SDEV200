import java.awt.*;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameSetup extends JFrame 
{
    private static final String DAREDECK_PATH = "/workspace/If_You_Dare!/src/dares.csv"; // Path to the CSV file
    private static final String LEADERBOARDS_PATH = "/workspace/If_You_Dare!/src/leaderboards.csv"; // Path to the gamesave CSV file
    Path leaderboardFile = Paths.get(LEADERBOARDS_PATH);
    protected Player winner;
    private JFrame mainMenuFrame;
    private JFrame gameModeFrame;
    private JFrame chooseDifficultyFrame;
    private JFrame chooseTimeLimitFrame;
    private JFrame playerSetupFrame;
    private JComboBox<Integer> playerQtyComboBox;
    private JComboBox<Integer> cpuQtyComboBox;
    private int chosenTimeLimit;
    private Integer[] playerOptions = {2, 3, 4, 5, 6};

    public GameSetup()
    {
        showMainMenu();
    }

    public void showMainMenu()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenuFrame = new JFrame("Main Menu");
        mainMenuFrame.setLayout(new GridLayout(3, 1));

        JLabel mainMenuLabel = new JLabel("Select Game Mode: ");
        JButton newGameButton = new JButton("New Game");
        JButton showLeaderboardsBtn = new JButton("Show Leaderboards");

        // Check if a previous game save exists
        File Leaderboard = new File(LEADERBOARDS_PATH);
        if (!Leaderboard.exists()) 
        {
            showLeaderboardsBtn.setEnabled(false);  // Disable resume if no save file exists
        } 
        else 
        {
            showLeaderboardsBtn.setEnabled(true);
        }

        // Button listeners
        newGameButton.addActionListener(e -> {
            if (!Leaderboard.exists()) 
            {
                Tutorial.main(null);
                showGameModes();
            }
            else 
            {
                showGameModes();
            }
        });
        mainMenuFrame.setVisible(false); // Hide the main menu

        mainMenuFrame.add(mainMenuLabel);
        mainMenuFrame.add(newGameButton);
        mainMenuFrame.add(showLeaderboardsBtn);

        mainMenuFrame.pack();
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }

    private void showGameModes() 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameModeFrame = new JFrame("Game Setup");
        gameModeFrame.setLayout(new GridLayout(4, 1));

        JLabel gameModeLabel = new JLabel("Select Game Mode: ");
        JButton classicButton = new JButton("Classic");
        JButton eliminationButton = new JButton("Elimination");
        JButton timeAttackButton = new JButton("Time Attack");

        // showDifficultySelection(Mode.TIME_ATTACK)

        classicButton.addActionListener(e -> showDifficultySelection(Mode.CLASSIC));
        eliminationButton.addActionListener(e -> showDifficultySelection(Mode.ELIMINATION));
        timeAttackButton.addActionListener(e -> chooseTimeLimit());

        gameModeFrame.add(gameModeLabel);
        gameModeFrame.add(classicButton);
        gameModeFrame.add(eliminationButton);
        gameModeFrame.add(timeAttackButton);

        // Initialize frame
        gameModeFrame.pack();
        gameModeFrame.setLocationRelativeTo(mainMenuFrame);
        gameModeFrame.setVisible(true);
    }

    private void showDifficultySelection(Mode gameMode) 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        chooseDifficultyFrame.setLocationRelativeTo(mainMenuFrame);
        chooseDifficultyFrame.setVisible(true);
    }

    private void chooseTimeLimit()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseTimeLimitFrame = new JFrame("Time Limit Setup");
        chooseTimeLimitFrame.setLayout(new GridLayout(4, 1));

        chooseTimeLimitFrame.dispose();  // Close the previous frame
        chooseTimeLimitFrame.setVisible(false);

        JLabel chooseTimeLimitLbl = new JLabel("Choose Time Limit: ");
        JButton twoMinsBtn = new JButton("2 Minutes");
        JButton fiveMinsBtn = new JButton("5 Minutes");
        JButton tenMinsBtn = new JButton("10 Minutes");

        twoMinsBtn.addActionListener(e -> setTimeLimit(2));
        fiveMinsBtn.addActionListener(e -> setTimeLimit(5));
        tenMinsBtn.addActionListener(e -> setTimeLimit(10));

        chooseTimeLimitFrame.add(chooseTimeLimitLbl);
        chooseTimeLimitFrame.add(twoMinsBtn);
        chooseTimeLimitFrame.add(fiveMinsBtn);
        chooseTimeLimitFrame.add(tenMinsBtn);
        
        chooseTimeLimitFrame.pack();
        chooseTimeLimitFrame.setLocationRelativeTo(mainMenuFrame);
        chooseTimeLimitFrame.setVisible(true);
    }

    private void setTimeLimit(int timeLimit)
    {
        chosenTimeLimit = timeLimit;
        showDifficultySelection(Mode.TIME_ATTACK);
        if(chooseTimeLimitFrame != null)
        {
            chooseTimeLimitFrame.dispose(); // hide timeLimit frame
            chooseTimeLimitFrame.setVisible(false);
        }
    }

    private void showPlayerSetup(Mode gameMode, Difficulty difficulty) 
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        playerSetupFrame.setLocationRelativeTo(mainMenuFrame);
        playerSetupFrame.setVisible(true);
    }

    private void updateCPUOptions() 
    {
        int localPlayerQty = (int) playerQtyComboBox.getSelectedItem();
        int maxCpuPlayers = 6 - localPlayerQty;
        Integer[] cpuOptions = new Integer[maxCpuPlayers + 1];
        for (int i = 0; i <= maxCpuPlayers; i++) {
            cpuOptions[i] = i;
        }
        cpuQtyComboBox.setModel(new DefaultComboBoxModel<>(cpuOptions));
    }

    private void startGame(Mode gameMode, Difficulty difficulty) 
    {
        int localPlayerQty = (int) playerQtyComboBox.getSelectedItem();
        int cpuPlayerQty = (int) cpuQtyComboBox.getSelectedItem();

        playerSetupFrame.dispose();
        playerSetupFrame.setVisible(false);

        List<Player> players = registerPlayers(localPlayerQty, cpuPlayerQty);

        List<DareCard> dareCards = DareCard.loadCsv(DAREDECK_PATH);
 
        GameMode game;
        switch (gameMode) 
        {
            case CLASSIC -> game = new Classic(difficulty, players, dareCards);
            case ELIMINATION -> game = new Elimination(difficulty, players, dareCards);
            case TIME_ATTACK -> game = new TimeAttack(difficulty, players, dareCards, chosenTimeLimit);
            default -> throw new IllegalArgumentException("Unknown game mode: " + gameMode);
        }

        while (!game.isGameOver()) 
        {
            game.playRound();
        }
        winner = game.determineWinner(); // determine the winner based on mode
        createFileIfNotExists(leaderboardFile);
        saveWinnerToLeaderboard(winner);
    }

    // Saving the winning player to the leaderboard file
    private void saveWinnerToLeaderboard(Player winner) {
        if (winner != null && winner.getPlayerName() != null && !winner.getPlayerName().isBlank()) {
            String entry = winner.getPlayerName() + "," + winner.getScore() + System.lineSeparator();
            byte[] data = entry.getBytes();

            // Write the winner's info to the leaderboard file
            try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(leaderboardFile, StandardOpenOption.APPEND))) {
                out.write(data);
            } catch (IOException e) {
                System.out.println("Error writing to leaderboard: " + e.getMessage());
            }
        }
    }

    // Helper method to create the leaderboard file if it doesn't exist
    private void createFileIfNotExists(Path file) {
        try {
            if (!Files.exists(file)) {
                Files.createFile(file);
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    private List<Player> registerPlayers(int playerCount, int cpuCount) {
        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= playerCount; i++) 
        {
            String localPlayerName = JOptionPane.showInputDialog(null, "Enter name for Local Player " + i + ":");
            if (localPlayerName != null && !localPlayerName.trim().isEmpty()) {
                players.add(new LocalPlayer(localPlayerName));
            } 
            else 
            {
                players.add(new LocalPlayer("Player " + i));
            }
        }
        for (int i = 1; i <= cpuCount; i++) 
        {
            players.add(new CPUPlayer());
        }
        return players;
    }
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(GameSetup::new);
    }
}