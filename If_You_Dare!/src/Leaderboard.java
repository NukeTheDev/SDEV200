import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.*;
import javax.swing.JOptionPane;

public class Leaderboard
{
    final String RANK_FORMAT = "000";
    final String POINTS_FORMAT = RANK_FORMAT;
    final String CARD_FORMAT = "00"; // Position on the leaderboards
    final String NAME_FORMAT = "          ";
    final int NAME_LENGTH = NAME_FORMAT.length();
    int HIGHEST_POINT_AMT;
    int HIGHEST_CARD_QTY;
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

    private Player winner;
    private Path leaderboardFile;
    public Leaderboard(Path file)
    {
        this.leaderboardFile = file;
        displayLeaderboard();
    }
    public Leaderboard(Player player, Path file)
    {
        this.winner = player;
        this.leaderboardFile = file;
        saveWinnerToLeaderboard(leaderboardFile);
        displayLeaderboard();
    }

    // Saving the winning player to the leaderboard file
    private void saveWinnerToLeaderboard(Path leaderboardFile) 
    {
        points = winner.getScore();
        cardQty = winner.getCardQty();
        createEmptyFile(leaderboardFile, s);
        try
        {
            fc = (FileChannel)Files.newByteChannel
                (leaderboardFile, CREATE, WRITE);

                String winnerName = winner.getPlayerName();
                int entryLength = winnerName.length(); 
                int missingSpaces = (NAME_LENGTH - entryLength);
                StringBuilder sb = new StringBuilder(name);
                sb.setLength(NAME_LENGTH);
                // If the string is longer than NAME_LENGTH, truncate it
                if (entryLength > NAME_LENGTH) 
                    sb.setLength(NAME_LENGTH);
                // If the string is shorter, append the necessary spaces
                else
                    sb.append(" ".repeat(missingSpaces));
                name = sb.toString();

                s = ++rank + delimiter + name + delimiter + points + delimiter +
                    cardQty + System.getProperty("line.separator");
                    byte data[] = s.getBytes();
                    ByteBuffer buffer = ByteBuffer.wrap(data);

                    fc.position(rank * RECSIZE);
                    fc.write(buffer);
        }
        catch (Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }

    public static void createEmptyFile(Path file, String s)
    {
        System.out.print("s = " + s);
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
                writer.write(s, 0, s.length());
            }
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }

    private void displayLeaderboard()
    {
        final String EMPTY_ACCT = "000";
        String[] array = new String[4];
        StringBuilder records = new StringBuilder(); // Moved here to accumulate all records

        try
        {
            InputStream iStream = new BufferedInputStream(Files.newInputStream(leaderboardFile));
            BufferedReader reader = new BufferedReader(new InputStreamReader(iStream));
            
            s = reader.readLine(); // Read the first line

            while (s != null)
            {
                array = s.split(delimiter);
                if (!array[0].equals(EMPTY_ACCT))
                {
                    int rank = Integer.parseInt(array[0]);
                    String playerName = array[1].trim(); // Remove extra spaces
                    int points = Integer.parseInt(array[2]);
                    int cardQty = Integer.parseInt(array[3]);

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

            // Display the accumulated leaderboard records in a JOptionPane
            JOptionPane.showMessageDialog(null, "Leaderboard:\n" + records.toString(), "Leaderboard", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (Exception e)
        {
            System.out.println("Error displaying leaderboard: " + e.getMessage());
        }
    }
}