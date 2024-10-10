// Charles "Nuke" Phillips
// p. 476
package ChapterEleven.Excercise11_7;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import static java.nio.file.StandardOpenOption.*;
import java.text.*;
import java.util.Scanner;

public class StudentsStanding 
{
    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);

        Path inGoodStandingFile =
            Paths.get(".\\" + "inGoodStanding.txt");
            Path onProbationFile = 
            Paths.get(".\\"+ "onProbation.txt");
        final String STUDENT_ID_FORMAT = "0000";
        final int STUDENT_ID_LENGTH = STUDENT_ID_FORMAT.length();
        final String NAME_FORMAT = "        ";
        final int NAME_LENGTH = NAME_FORMAT.length();
        final double PASSING_GRADE = 2.0;
        final String GPA_FORMAT = "0.00";
        String delimiter = ",";
        String s = STUDENT_ID_FORMAT + delimiter + NAME_FORMAT +
            delimiter + NAME_FORMAT + delimiter + GPA_FORMAT +
            System.getProperty("line.separator");
        final int RECSIZE = s.length();

        FileChannel fcIn = null;
        FileChannel fcOn = null;
        String idString;
        String lastName = ""; String firstName = "";
        int id;
        double gpa;
        final String QUIT = "999";

        createEmptyFile(inGoodStandingFile, s);
        createEmptyFile(onProbationFile, s);

        try
        {
            fcIn = (FileChannel)Files.newByteChannel
                (inGoodStandingFile, CREATE, WRITE);
            fcOn = (FileChannel)Files.newByteChannel
                (onProbationFile, CREATE, WRITE);
            
            System.out.print("Enter student ID # >> ");
            while (true) 
            {
                idString = input.nextLine();
                if ((idString.length() == STUDENT_ID_LENGTH) && (!(idString.matches("[a-zA-Z]+"))))
                { // Check if the student ID number fits format
                    break;
                } 
                else 
                {
                    System.out.printf("Invalid student ID number. It needs to be %d digits long >> ", STUDENT_ID_LENGTH);
                }
            }

            while(!(idString.equals(QUIT)))
            {
                id = Integer.parseInt(idString);

                for(int i = 0; i < 2; ++i)
                {
                    String name;
                    System.out.printf("Enter the student's %s name >> ", (i == 0) ? "last" : "first");
                    // Declarations of array to track value location
                    name = input.nextLine();
                    int entryLength = name.length(); 
                    int missingSpaces = (NAME_LENGTH - entryLength);
                    StringBuilder sb = new StringBuilder(name);
                    // If the string is longer than NAME_LENGTH, truncate it
                    if (entryLength > NAME_LENGTH) 
                    {
                        sb.setLength(NAME_LENGTH);
                    }
                    else
                    {
                        sb.append(" ".repeat(missingSpaces));
                    }
                    switch(i){
                        case 0 -> {
                            lastName = sb.toString();
                            break;
                        }
                        case 1 -> {
                            firstName = sb.toString();
                            break;
                        }
                    }
                }

                System.out.print("Enter GPA >> ");
                gpa = input.nextDouble();
                input.nextLine();
                DecimalFormat df = new DecimalFormat(GPA_FORMAT);

                s = idString + delimiter + lastName + delimiter + firstName + delimiter +
                    df.format(gpa) + System.getProperty("line.separator");
                byte data[] = s.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(data);

                // This uses a student's GPA to determine whether 
                //or not a student is in good standing.
                if(gpa >= PASSING_GRADE)
                {
                    fcIn.position(id * RECSIZE);
                    fcIn.write(buffer);
                }
                else
                {
                    fcOn.position(id * RECSIZE);
                    fcOn.write(buffer);
                }
                System.out.print("Enter next student ID # or " +
                    QUIT + " to quit >> ");
                while (true) 
                {
                    idString = input.nextLine();
                    if ((idString.length() ==  STUDENT_ID_LENGTH || idString.length() ==  QUIT.length()) && (!(idString.matches("[a-zA-Z]+"))))
                    { // Check if student ID number fits format
                        break;
                    } 
                    else 
                    {
                        System.out.printf("Invalid student ID number. It needs to be %d digits long >> ", STUDENT_ID_LENGTH);
                    }
                }
            }
            fcIn.close();
            fcOn.close();
        }
        catch (Exception e)
        {
            System.out.println("Error message: " + e);
        }
    }

    public static void createEmptyFile(Path file, String s)
    {
        final int NUMRECS = 10000;
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
