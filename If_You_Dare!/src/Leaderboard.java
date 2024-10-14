import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardOpenOption.*;
import javax.swing.JOptionPane;

public class Leaderboard
{
    private Mode gameMode;
    Path leaderboardFile = Paths.get("/workspace/If_You_Dare!/src/" + gameMode + "leaderboard.csv");
    private Player winner;
    final String RANK_FORMAT = "000";
    final String POINTS_FORMAT = RANK_FORMAT;
    final String CARD_FORMAT = "00"; // Position on the leaderboards
    final String NAME_FORMAT = "          ";
    final int NAME_LENGTH = NAME_FORMAT.length();
    int HIGHEST_POINT_TOTAL = 0;
    int HIGHEST_CARD_QTY = 0;
    String delimiter = ",";
    String s = RANK_FORMAT + delimiter + NAME_FORMAT +
    delimiter + POINTS_FORMAT + delimiter + CARD_FORMAT +
    System.getProperty("line.separator");
    final int RECSIZE = s.length();
    FileChannel fc = null;
    int rank;
    String name;
    int points;
    double cardQty;
    public Leaderboard(Path file)
    {
        this.leaderboardFile = file;
        getLeaderboard("display");
    }
    public Leaderboard(Player player, Mode mode)
    {
        this.gameMode = mode;
        this.winner = player;
        saveWinnerToLeaderboard();
        getLeaderboard("display");
    }

    // Saving the winning player to the leaderboard file

    // Testing: winner 000, Charles , 235, 17
    // Testing winner 0001, Rebecca, 185, 21
    private void saveWinnerToLeaderboard() 
    {
        File leaderboardFileObj = new File("/workspace/If_You_Dare!/src/" + gameMode + "leaderboard.csv");
        if (!leaderboardFileObj.exists()) {
            createEmptyFile(leaderboardFile, s);
        } else {
            getLeaderboard("update ranking"); // Read the leaderboard to update ranks
        }

        points = winner.getScore();
        cardQty = winner.getCardQty();
        String winnerName = winner.getPlayerName();
        updateRankAndSave(winnerName);
        System.out.println("gameMode is: " + gameMode);
        points = winner.getScore();
        cardQty = winner.getCardQty();
    }
    private void updateRankAndSave(String winnerName)
    {
        try
        {
            fc = (FileChannel)Files.newByteChannel
                (leaderboardFile, CREATE, WRITE);

                int entryLength = winnerName.length(); 
                int missingSpaces = (NAME_LENGTH - entryLength);
                StringBuilder sb = new StringBuilder(name);
                // If the string is longer than NAME_LENGTH, truncate it
                if (entryLength > NAME_LENGTH) 
                    sb.setLength(NAME_LENGTH);
                // If the string is shorter, append the necessary spaces
                else
                    sb.append(" ".repeat(missingSpaces));
                name = sb.toString();

                s = rank + delimiter + name + delimiter + points + delimiter +
                    cardQty + System.getProperty("line.separator");
                    byte data[] = s.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(data);

                    fc.position((rank - 1) * RECSIZE);
                    fc.write(buffer);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error message: " + e + "Error saving record to leaderboard!" + JOptionPane.WARNING_MESSAGE);
        }
    }

    // Logic for determining rank on the leaderboard
    private int getHighestPointTotal(int newTotal) {
        if (newTotal > HIGHEST_POINT_TOTAL) {
            HIGHEST_POINT_TOTAL = newTotal;
        }
        return HIGHEST_POINT_TOTAL;
    }

    private int getHighestCardQty(int newQty) {
        if (newQty > HIGHEST_CARD_QTY) {
            HIGHEST_CARD_QTY = newQty;
        }
        return HIGHEST_CARD_QTY;
    }
    // Setters for setting new highest point totals and card quantities
    private void setHighestPointTotal(int tot)
    {
        HIGHEST_POINT_TOTAL = tot;
    }
    private void setHighestCardQty(int qty)
    {
        HIGHEST_CARD_QTY = qty;
    }

    private void getLeaderboard(String command)
    {
        System.out.println("gameMode is: " + gameMode);
        final String EMPTY_RECORD = "000";
        String[] array = new String[4];
        StringBuilder records = new StringBuilder(); // Moved here to accumulate all records

        try
        {
            InputStream iStream = new BufferedInputStream(Files.newInputStream(leaderboardFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            
            s = reader.readLine(); // Read the first line
            int rankCounter = 0; // Counter for assigning ranks

            while (s != null) {
                array = s.split(delimiter);
                if (!array[0].equals(EMPTY_RECORD)) 
                {
                    rankCounter++; // Increment rank for every valid entry
                    int rank = Integer.parseInt(array[0]);
                    String playerName = array[1].trim(); // Remove extra spaces
                    int points = Integer.parseInt(array[2]);
                    int cardQty = Integer.parseInt(array[3]);
                    
                    // Update highest totals
                    setHighestPointTotal(getHighestPointTotal(points));
                    setHighestCardQty(getHighestCardQty(cardQty));
                    
                    // Determine if this player is in the top spot
                    if (points >= HIGHEST_POINT_TOTAL && cardQty >= HIGHEST_CARD_QTY) 
                        rank = 1; // Assign top spot
                    else
                        rank = rankCounter; // Normal rank based on order of appearance

                    // Append the current player's details to the records StringBuilder
                    records.append("Pos: ").append(rank)
                        .append(" | Player: ").append(playerName)
                        .append(" | Score: ").append(points)
                        .append(" | Cards: ").append(cardQty)
                        .append("\n");
                }
                s = reader.readLine(); // Read the next line
            }    
            reader.close();
            if("display".equals(command))
            {
                // Display the accumulated leaderboard records in a JOptionPane
                JOptionPane.showMessageDialog(null, "Leaderboard:\n" + records.toString(), gameMode + "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception e)
        {
            // Display the accumulated leaderboard records in a JOptionPane
            JOptionPane.showMessageDialog(null, "Error displaying the leaderboard for " + gameMode + e.getMessage(), "If You Dare Says:", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void createEmptyFile(Path file, String s)
    {
        System.out.print("file: " + file);
        final int NUMRECS = 100; // top 100 players
        try
        {
            OutputStream outputStr =
                new BufferedOutputStream
                    (Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(outputStr));
            for(int count = 0; count < NUMRECS; ++count)
            {
                writer.write(s);
            }
            writer.close();
        }
        catch(Exception e)
        {
            // Display the accumulated leaderboard records in a JOptionPane
            JOptionPane.showMessageDialog(null, "Error creating empty file: \n" + e.getMessage(), "If You Dare Says:", JOptionPane.WARNING_MESSAGE);
        }
    }
}