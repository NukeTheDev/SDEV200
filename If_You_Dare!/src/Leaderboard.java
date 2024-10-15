// Charles "Nuke" Phillips

/* Program description:
 *
 * The Leaderboard class manages the tracking and display of player rankings
 * based on their scores and number of cards. It can update the leaderboard with
 * new winners, save player data to a CSV file, and display the leaderboard in a
 * formatted view using a graphical message box. It ensures rank calculation is
 * dynamic and maintains top player records.
 */

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import javax.swing.JOptionPane;

public class Leaderboard {
    public Mode gameMode;
    Path leaderboardFile;
    private Player winner;
    final String RANK_FORMAT = "000";
    final String POINTS_FORMAT = RANK_FORMAT;
    final String CARD_FORMAT = "00"; 
    final String NAME_FORMAT = "          ";
    final int NAME_LENGTH = NAME_FORMAT.length();
    String delimiter = ",";
    String s = RANK_FORMAT + delimiter + NAME_FORMAT +
               delimiter + POINTS_FORMAT + delimiter + CARD_FORMAT +
               System.getProperty("line.separator");
    final int RECSIZE = s.length();
    FileChannel fc = null;
    int rank = 000;
    String name;
    int points;
    double cardQty;

    // Constructor for displaying leaderboard
    public Leaderboard(Mode mode) {
        this.gameMode = mode;
        setLeaderboardFilePath(getLeaderboardFile(mode));
        displayLeaderboard();
    }

    // Constructor for updating leaderboard with a new player
    public Leaderboard(Player player, Mode mode) {
        this.gameMode = mode;
        this.winner = player;
        setLeaderboardFilePath(getLeaderboardFile(mode));
        updateRankAndSave(winner); // Update rank and save on initialization
        displayLeaderboard();
    }

    private Path getLeaderboardFile(Mode mode) {
        Path leaderboardFile = Paths.get(".\\" + mode + "_leaderboard.csv");
        return leaderboardFile;
    }

    private void setLeaderboardFilePath(Path path) {
        this.leaderboardFile = path;
    }

    // Method to update the rank and save the player info
    private void updateRankAndSave(Player player) {
        // Ensures the leaderboard always exists
        File leaderboardFileObj = new File(".\\" + gameMode + "_leaderboard.csv");
        if (!leaderboardFileObj.exists())
            createEmptyFile(leaderboardFile, s);
        int points = player.getScore();
        int cardQty = player.getCardQty();
        String winnerName = player.getPlayerName();
        int rank = getLeaderboard("determine ranking", points, cardQty);

        saveRecord(winnerName, points, cardQty, rank);
    }

    // Method to save a player's details in the leaderboard
    private void saveRecord(String winnerName, int points, int cardQty, int rank) {
        try {
            fc = (FileChannel)Files.newByteChannel(leaderboardFile, CREATE, WRITE);
            name = winnerName;
            int entryLength = winnerName.length();
            int missingSpaces = (NAME_LENGTH - entryLength);
            StringBuilder sb = new StringBuilder(name);

            // Truncate or pad the name to match the format
            if (entryLength > NAME_LENGTH) 
                sb.setLength(NAME_LENGTH);
            else
                sb.append(" ".repeat(missingSpaces));
            name = sb.toString();

            s = String.format("%03d", rank) + delimiter + name + delimiter + 
                points + delimiter + cardQty + System.getProperty("line.separator");
            byte data[] = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);

            // Correctly position the file channel and ensure no extra characters are written
            fc.position((rank - 1) * RECSIZE); // Save at the correct position based on rank
            fc.write(buffer);

            fc.close(); // Close the FileChannel after writing to avoid errors
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error message: " + e + " Error saving record to leaderboard!" + JOptionPane.WARNING_MESSAGE);
        }
    }

    // Method to display the leaderboard
    private void displayLeaderboard() {
        getLeaderboard("display", 0, 0); // Call with 0s for display purposes
    }

    // Method to determine rank or display the leaderboard
    private int getLeaderboard(String command, int points, int cardQty) {
        final String EMPTY_RECORD = "000";
        String[] array = new String[4];
        StringBuilder records = new StringBuilder(); // To accumulate all records
        int determinedRank = 0; // Counter for assigning ranks
        int rankCounter = 0; // Initialize rank counter for tracking

        try {
            InputStream iStream = new BufferedInputStream(Files.newInputStream(leaderboardFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            
            s = reader.readLine(); // Read the first line

            while (s != null) {
                array = s.split(delimiter);
                if (!array[0].equals(EMPTY_RECORD)) {
                    rankCounter++; // Increment rank for every valid entry
                    String playerName = array[1].trim();
                    int recordPoints = Integer.parseInt(array[2].trim());
                    int recordCardQty = Integer.parseInt(array[3].trim());

                    // If determining rank, check if the current player's score and card quantity warrant a rank change
                    if ("determine ranking".equals(command) && points >= recordPoints && cardQty >= recordCardQty)
                        determinedRank = rankCounter; // Assign the determined rank

                    // Append the current player's details to the records StringBuilder for display purposes
                    records.append("Pos: ").append(rankCounter)
                           .append(" | Player: ").append(playerName)
                           .append(" | Score: ").append(recordPoints)
                           .append(" | Cards: ").append(recordCardQty)
                           .append("\n");
                }
                s = reader.readLine(); // Read the next line
            }

            reader.close();

            if ("display".equals(command)) {
                // Display the accumulated leaderboard records in a JOptionPane
                JOptionPane.showMessageDialog(null, "Leaderboard:\n" + records.toString(), gameMode + " Leaderboard", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            // Display error message
            System.out.println(e);
        }

        return determinedRank == 0 ? rankCounter + 1 : determinedRank; // Return the determined rank or the next available rank
    }

    // Method to create an empty leaderboard file with placeholder records
    public static void createEmptyFile(Path file, String s) {
        final int NUMRECS = 100; // Top 100 players
        try {
            OutputStream outputStr = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStr));
            for (int count = 0; count < NUMRECS; ++count) {
                writer.write(s);
            }
            writer.close();
        } catch (Exception e) {
            // Display error message
            JOptionPane.showMessageDialog(null, "Error creating empty file: \n" + e.getMessage(), "If You Dare Says:", JOptionPane.WARNING_MESSAGE);
        }
    }
}