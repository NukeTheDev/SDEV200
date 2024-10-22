package ChapterEleven.Tutorial11;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.text.*;
import java.util.Scanner;

public class CreateFilesBasedOnState
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Path inStateFile = 
            Paths.get(".\\inStateCusts.txt");
            Path outOfStateFile = 
            Paths.get(".\\outOfStateCusts.txt");
        final String ID_FORMAT = "000";
        final String NAME_FORMAT = "          ";
        final int NAME_LENGTH = NAME_FORMAT.length();
        final String HOME_STATE = "WI";
        final String BALANCE_FORMAT = "0000.00";
        String delimiter = ",";
        String s = ID_FORMAT + delimiter + NAME_FORMAT +
            delimiter + HOME_STATE + delimiter + BALANCE_FORMAT +
            System.getProperty("line.separator");
        final int RECSIZE = s.length();

        FileChannel fcIn = null;
        FileChannel fcOut = null;
        String idString;
        int id;
        String name;
        String state;
        double balance;
        final String QUIT = "999";

        createEmptyFile(inStateFile, s);
        createEmptyFile(outOfStateFile, s);

        try
        {
            fcIn = (FileChannel)Files.newByteChannel
                (inStateFile, CREATE, WRITE);
            fcOut = (FileChannel)Files.newByteChannel
                (outOfStateFile, CREATE, WRITE);

            System.out.print("Enter customer account number >> ");
            while (true) 
            {
                idString = input.nextLine();
                if (idString.length() == 3 && (!(idString.matches("[a-zA-Z]+"))))
                { // Check if bank account number fits format
                    break;
                } 
                else 
                {
                    System.out.printf("Invalid account number. It needs to be three digits long >> ");
                }
            }

            while(!(idString.equals(QUIT)))
            {
                id = Integer.parseInt(idString);

                System.out.print("Enter name for customer >> ");
                name = input.nextLine();
                int entryLength = name.length(); 
                int missingSpaces = (NAME_LENGTH - entryLength);
                StringBuilder sb = new StringBuilder(name);
                // If the string is longer than NAME_LENGTH, truncate it
                if (entryLength > NAME_LENGTH) 
                    sb.setLength(NAME_LENGTH);
                // If the string is shorter, append the necessary spaces
                else
                    sb.append(" ".repeat(missingSpaces));
                name = sb.toString();

                System.out.println("Enter state >> ");
                state = input.nextLine();

                System.out.print("Enter balance >> ");
                balance = input.nextDouble();
                input.nextLine();
                DecimalFormat df = new DecimalFormat(BALANCE_FORMAT);

                s = idString + delimiter + name + delimiter + state + delimiter +
                    df.format(balance) + System.getProperty("line.separator");
                byte data[] = s.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(data);

                if(state.equals(HOME_STATE))
                {
                    fcIn.position(id * RECSIZE);
                    fcIn.write(buffer);
                }
                else
                {
                    fcOut.position(id * RECSIZE);
                    fcOut.write(buffer);
                }
                System.out.print("Enter next customer account number or " +
                    QUIT + " to quit >> ");
                while (true) 
                {
                    idString = input.nextLine();
                    if (idString.length() == 3 && (!(idString.matches("[a-zA-Z]+"))))
                    { // Check if bank account number fits format
                        break;
                    } 
                    else 
                    {
                        System.out.printf("Invalid account number. It needs to be three digits long >> ");
                    }
                }
            }
            fcIn.close();
            fcOut.close();
        }
        catch (Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }

    public static void createEmptyFile(Path file, String s)
    {
        System.out.print("s = " + s);
        final int NUMRECS = 100;
        try
        {
            OutputStream outputStr =
                new BufferedOutputStream
                    (Files.newOutputStream(file, CREATE));
                BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(outputStr));
            for(int count = 0; count < NUMRECS; ++count)
                writer.write(s, 0, s.length());
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }
}
