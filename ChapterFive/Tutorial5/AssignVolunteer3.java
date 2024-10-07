// Charles "Nuke" Phillips
// p. 167
package ChapterFive.Tutorial5;
import java.util.Scanner;

public class AssignVolunteer3
{
    public static void main(String[] args) 
    {
        int donationType;
        String volunteer;
        String message;
        final int CLOTHING_CODE = 1;
        final int OTHER_CODE = 2;
        final String CLOTHING_PRICER = "Regina";
        final String OTHER_PRICER = "Marco";
        Scanner input = new Scanner(System.in);
        System.out.println("What type of donation is this?");
        System.out.print("Enter " + CLOTHING_CODE +
        " for clothing, or " + OTHER_CODE + " for anything else... ");
            
        if (input.hasNextInt()) 
        {  // Check if the input is an integer
            donationType = input.nextInt();  // Capture the integer input
            if (donationType == CLOTHING_CODE) 
            {
                volunteer = CLOTHING_PRICER;
                message = "a clothing donation";
            } 
            else if (donationType == OTHER_CODE) 
            {
                volunteer = OTHER_PRICER;
                message = "a non-clothing donation";
            } 
            else 
            {
                volunteer = "invalid";
                message = "an invalid donation type";
            }
        } 
        else 
        {
            System.out.println("Invalid input! The input is not an integer!");
            input.next();  // Consume the invalid input to avoid issues
            donationType = -1;  // Optionally assign a default value to indicate error upon string entry
            volunteer = "invalid";
            message = "an invalid donation type";
        }
        System.out.println("You entered " + donationType);
        System.out.println("This is " + message);
        System.out.println("The volunteer who will price this is " + volunteer);
    }
}


