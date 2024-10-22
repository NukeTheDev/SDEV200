/* Program description:
 *
 * The Leaderboard class manages the tracking and display of player rankings
 * based on their scores and number of cards. It can update the leaderboard with
 * new players, save player data to a CSV file, and display the leaderboard in a
 * formatted view using a graphical message box. It ensures rank calculation is
 * dynamic and maintains the top 100 players for the selected game mode.
 */

 import java.io.*;
 import java.nio.ByteBuffer;
 import java.nio.channels.FileChannel;
 import java.nio.file.Files;
 import java.nio.file.Path;
 import java.nio.file.Paths;
 import static java.nio.file.StandardOpenOption.*;
 import java.util.List;
 import javax.swing.JOptionPane;
 
 public class Leaderboard {
     public Mode gameMode;
     Path leaderboardFile;
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
     int rank;
     String name;
     int score;
     double cardQty;
 
     // Constructor for displaying leaderboard
     public Leaderboard(Mode mode) {
         this.gameMode = mode;
         setLeaderboardFilePath(getLeaderboardFile(mode));
         displayLeaderboard();
     }
 
     // Constructor for updating leaderboard with multiple players
     public Leaderboard(List<Player> players, Mode mode) {
         this.gameMode = mode;
         setLeaderboardFilePath(getLeaderboardFile(mode));
         // Iterate through all players to update leaderboard
         for (Player player : players) {
             updateRankAndSave(player);
         } 
         displayLeaderboard();
     }

     // Function for displaying the leaderboard
     private void displayLeaderboard()
     {
        getLeaderboard("display", 0, 0);
     }
 
     private Path getLeaderboardFile(Mode mode) {
         return Paths.get("/workspace/If_You_Dare!/src/" + gameMode + "_leaderboard.csv");
     }
 
     private void setLeaderboardFilePath(Path path) 
     {
        this.leaderboardFile = path;
     }
 
     // Method to update the rank and save the player info based on the command
     private void updateRankAndSave(Player player) 
     {
         if (player != null) 
         {
             int score = player.getScore();
             int cardQty = player.getCardQty();
             String playerName = player.getPlayerName();
             int rank = (getLeaderboard("determine rank", score, cardQty));
 
             // Ensure the leaderboard file exists
             File leaderboardFileObj = new File("/workspace/If_You_Dare!/src/" + gameMode + "_leaderboard.csv");
             if (!leaderboardFileObj.exists())
                 createEmptyFile(leaderboardFile, s);
 
             // Save the player's record
             if (rank <= 100)  // Ensure only the top 100 players are saved
                 saveRecord(rank, playerName, score, cardQty); 
        }
    }
 
    // Method to save a player's details in the leaderboard
    private void saveRecord(int rank, String playerName, int score, int cardQty) {
        try {
            // Open the file channel for both reading and writing
            fc = (FileChannel)Files.newByteChannel(leaderboardFile, CREATE, WRITE, READ);
            
            // Prepare the player's name to ensure it fits within the fixed length
            name = playerName;
            int entryLength = playerName.length();
            int missingSpaces = NAME_LENGTH - entryLength;
            StringBuilder sb = new StringBuilder(name);
    
            // Truncate or pad the name to match the format
            if (entryLength > NAME_LENGTH) {
                sb.setLength(NAME_LENGTH);
            } else {
                sb.append(" ".repeat(missingSpaces));
            }
            name = sb.toString();
    
            // Format the player's data into the CSV format
            s = String.format("%03d", rank) + delimiter + name + delimiter +
                String.format("%03d", score) + delimiter + 
                String.format("%02d", cardQty) + System.getProperty("line.separator");
            
            byte[] data = s.getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(data);
    
            // Position the file channel to the correct rank and truncate at that point
            long writePosition = (rank - 1) * RECSIZE;
            fc.position(writePosition);
    
            // Write the buffer data
            fc.write(buffer);
    
            // Ensure truncation to avoid leftover bytes (e.g., from previous records)
            long newFileSize = writePosition + data.length;
            fc.truncate(newFileSize);
    
            // Clear the buffer and close the channel
            buffer.clear();
            fc.close();
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error message: " + e + " Error saving record to leaderboard!" + JOptionPane.WARNING_MESSAGE);
        }
    }    
    
     // Method to determine rank or display the leaderboard
     private int getLeaderboard(String command, int score, int cardQty) {
         final String EMPTY_RECORD = "000";
         String[] array = new String[4];
         StringBuilder records = new StringBuilder();
         int determinedRank = 0;
         int rankCounter = 0;
 
         try {
             InputStream iStream = new BufferedInputStream(Files.newInputStream(leaderboardFile));
             BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
             s = reader.readLine();
 
             while (s != null) 
             {
                 array = s.split(delimiter);
                 if (!array[0].equals(EMPTY_RECORD)) 
                 {
                     rankCounter++;
                     String playerName = array[1].trim();
                     int recordPoints = Integer.parseInt(array[2].trim());
                     int recordCardQty = Integer.parseInt(array[3].trim());
 
                     // Rank players based on score and cards
                     if ("determine rank".equals(command) && (score > recordPoints || (score == recordPoints && cardQty >= recordCardQty)))
                         determinedRank = rankCounter;
 
                     records.append("Pos: ").append(rankCounter)
                            .append(" | Player: ").append(playerName)
                            .append(" | Score: ").append(recordPoints)
                            .append(" | Cards: ").append(recordCardQty)
                            .append("\n");
                 }
                 s = reader.readLine();
             }
 
             reader.close();
 
             if ("display".equals(command)) 
             {
                 JOptionPane.showMessageDialog(null, "Leaderboard:\n" + records.toString(), gameMode + " Leaderboard", JOptionPane.INFORMATION_MESSAGE);
             }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Error displaying the leaderboard: " + e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
         }
 
         return determinedRank == 0 ? rankCounter + 1 : determinedRank;
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
             JOptionPane.showMessageDialog(null, "Error creating empty file: \n" + e.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
         }
     }
 }
 