// Charles "Nuke" Phillips
// SDEV 200 Final Project

/* App description:
 *
 * If You Dare is a turn-based computer game where players must complete challenges
 * based on difficulty levels: EASY, MEDIUM, or HARD. Each dare has a time limit,
 * category, and point value. Depending on the difficulty chosen, dare crds are drawn
 * and shown to the hotseat player. 
 * 
 * The goal of the game depends on the selected mode.
 *
 * Players accumulate points by completing dares, and 
 * the game tracks progress until a winner is determined. The game also includes 
 * a leaderboard that stores and displays top players' scores.
 */

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class GameSetup extends JFrame
{
    Tutorial tutorialManager = new Tutorial();
    private static final String DAREDECK_PATH = ".\\dares.csv"; // Path to the CSV file
    private JFrame mainMenuFrame;
    private JFrame gameModeFrame;
    private JFrame chooseDifficultyFrame;
    private JFrame chooseTimeLimitFrame;
    private JFrame playerSetupFrame;
    private JComboBox<Integer> playerQtyComboBox;
    private JComboBox<Integer> cpuQtyComboBox;
    private int chosenTimeLimit;
    private Integer[] cpuOptions = {0, 1, 2, 3, 4};
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

        newGameButton.addActionListener(e -> showGameModes("default"));
        showLeaderboardsBtn.addActionListener(e -> showGameModes("showLeaderboards"));

        mainMenuFrame.add(mainMenuLabel);
        mainMenuFrame.add(newGameButton);
        mainMenuFrame.add(showLeaderboardsBtn);

        mainMenuFrame.pack();
        mainMenuFrame.setLocationRelativeTo(null);
        mainMenuFrame.setVisible(true);
    }

    private void showGameModes(String command) 
    {
        if(gameModeFrame != null)
        {
            gameModeFrame.setVisible(false);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameModeFrame = new JFrame("Game Setup");
        gameModeFrame.setLayout(new GridLayout(5, 1));

        JLabel gameModeLabel = new JLabel("Select Game Mode: ");
        JButton classicBtn = new JButton("Classic");
        JButton eliminationBtn = new JButton("Elimination");
        JButton timeAttackBtn = new JButton("Time Attack");
        JButton backBtn = new JButton("<-");

        // showDifficultySelection(Mode.TIME_ATTACK)
        if("showLeaderboards".equals(command))
        {
            classicBtn.addActionListener(e -> showLeaderboard(Mode.CLASSIC));
            eliminationBtn.addActionListener(e -> showLeaderboard(Mode.ELIMINATION));
            timeAttackBtn.addActionListener(e -> showLeaderboard(Mode.TIME_ATTACK));
            backBtn.addActionListener(e -> showMainMenu());
        }
        else
        {
            classicBtn.addActionListener(e -> showDifficultySelection(Mode.CLASSIC));
            eliminationBtn.addActionListener(e -> showDifficultySelection(Mode.ELIMINATION));
            timeAttackBtn.addActionListener(e -> chooseTimeLimit());
            backBtn.addActionListener(e -> showMainMenu());
        }


        gameModeFrame.add(gameModeLabel);
        gameModeFrame.add(classicBtn);
        gameModeFrame.add(eliminationBtn);
        gameModeFrame.add(timeAttackBtn);
        gameModeFrame.add(backBtn);


        // Initialize frame
        gameModeFrame.pack();
        gameModeFrame.setLocationRelativeTo(mainMenuFrame);
        gameModeFrame.setVisible(true);
    }

    private void showLeaderboard(Mode gameMode)
    {
        // Check if a leaderBoardFile exists
        File Leaderboard = new File(".\\" + gameMode + "_leaderboard.csv");
        if (!Leaderboard.exists()) 
        {
            JOptionPane.showMessageDialog(null, "No records found for " + gameMode + "!");
        } 
        else 
        {
            // Display leaderboard for the selected game mode
            Leaderboard leaderBoard = new Leaderboard(gameMode);
        }
    }

    private void showDifficultySelection(Mode gameMode) 
    {
        if(chooseDifficultyFrame != null)
        {
            chooseDifficultyFrame.setVisible(false);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseDifficultyFrame = new JFrame("Choose Difficulty");
        chooseDifficultyFrame.setLayout(new GridLayout(5, 1));

        gameModeFrame.dispose();  // Close the previous frame
        gameModeFrame.setVisible(false);

        JLabel chooseDifficultyLabel = new JLabel("Choose Difficulty: ");
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");
        JButton backBtn = new JButton("<-");

    
        easyButton.addActionListener(e -> showPlayerSetup(gameMode, Difficulty.EASY));
        mediumButton.addActionListener(e -> showPlayerSetup(gameMode, Difficulty.MEDIUM));
        hardButton.addActionListener(e -> showPlayerSetup(gameMode, Difficulty.HARD));
        backBtn.addActionListener(e -> showGameModes("default"));

        chooseDifficultyFrame.add(chooseDifficultyLabel);
        chooseDifficultyFrame.add(easyButton);
        chooseDifficultyFrame.add(mediumButton);
        chooseDifficultyFrame.add(hardButton);
        chooseDifficultyFrame.add(backBtn);
        
        chooseDifficultyFrame.pack();
        chooseDifficultyFrame.setLocationRelativeTo(mainMenuFrame);
        chooseDifficultyFrame.setVisible(true);
    }

    private void chooseTimeLimit()
    {
        if(chooseTimeLimitFrame != null)
        {
            chooseTimeLimitFrame.setVisible(false);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chooseTimeLimitFrame = new JFrame("Time Limit Setup");
        chooseTimeLimitFrame.setLayout(new GridLayout(5, 1));

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
        if(playerSetupFrame != null)
        {
            playerSetupFrame.setVisible(false);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        playerSetupFrame = new JFrame("Player Setup");
        playerSetupFrame.setLayout(new GridLayout(5, 1));

        chooseDifficultyFrame.dispose();  // Close the previous frame
        chooseDifficultyFrame.setVisible(false); // Close the previous frame

        playerQtyComboBox = new JComboBox<>(playerOptions);
        playerSetupFrame.add(new JLabel("How many local players?"));
        playerSetupFrame.add(playerQtyComboBox);

        cpuQtyComboBox = new JComboBox<>(cpuOptions);
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
        File Leaderboard = new File(".\\" + gameMode + "_leaderboard.csv");
        if (!Leaderboard.exists()) 
        {
            tutorialManager.playTutorial(gameMode);
        }

        int localPlayerQty = (int) playerQtyComboBox.getSelectedItem();
        int cpuPlayerQty = (int) cpuQtyComboBox.getSelectedItem();

        playerSetupFrame.dispose();
        playerSetupFrame.setVisible(false);

        List<Player> players = registerPlayers(localPlayerQty, cpuPlayerQty);

        List<DareCard> dareCards = DareCard.loadCsv(DAREDECK_PATH, difficulty);
 
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
        System.out.println("Went here");
        Player winner = game.getWinner();
        Leaderboard leaderBoard = new Leaderboard(winner, gameMode);
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