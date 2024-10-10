// Charles "Nuke" Phillips
// p. 476
package ChapterEleven.Excercise11_7;

import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.*;

public class StudentsStanding2
{
    public static void main(String[] args) 
    {
        final String STUDENT_ID_FORMAT = "0000";
        final String NAME_FORMAT = "        ";
        final double PASSING_GRADE = 2.0;
        final String GPA_FORMAT = "0.00";
        String delimiter = ",";
        String s = STUDENT_ID_FORMAT + delimiter + NAME_FORMAT +
            delimiter + NAME_FORMAT + delimiter + GPA_FORMAT +
            System.getProperty("line.separator");

        final String EMPTY_ACCT = "0000";
        String[] array = new String[4];
        double gpa;

        // Files to read
        String[] files = {"inGoodStanding.txt", "onProbation.txt"};
        // Loop through each file
        for (String fileName : files)
        {
            Path file = Paths.get(".\\" + fileName); // Use "./" for current directory relative path

            // Display file attributes
            try 
            {
                BasicFileAttributes attr = Files.readAttributes(file, BasicFileAttributes.class);
                System.out.println("\nAttributes of the file: " + fileName);
                System.out.println("Creation time: " + attr.creationTime());
                System.out.println("Size: " + attr.size() + " bytes");
            } 
            catch (IOException e) 
            {
            System.out.println("IO Exception");
            }
            // Read and process the file content
            try
            {
                InputStream iStream = new 
                    BufferedInputStream(Files.newInputStream(file));
                BufferedReader reader = new 
                    BufferedReader(new InputStreamReader(iStream));

                System.out.println("\nStudent records from: " + fileName);
                // Read each line from the file
                s = reader.readLine();
                while (s != null)
                {
                    array = s.split(delimiter);  // Split the line into fields
                    // Check if the record is non-default
                    if (!array[0].equals(EMPTY_ACCT))
                    {
                        gpa = Double.parseDouble(array[3]);
                        System.out.printf("ID #%s %s %s GPA: %.2f %s the passing grade by %.2f\n", 
                            array[0], array[1].trim(), array[2].trim(), gpa,
                            (gpa >= PASSING_GRADE) ? "exceeds" : "falls short of", Math.abs(gpa - PASSING_GRADE));
                    }
                    s = reader.readLine();  // Read the next line
                }
                reader.close();
            } 
            catch(Exception e)
            {
            System.out.println("Message: " + e);
            }
        }
    }
}
