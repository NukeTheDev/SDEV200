// Charles "Nuke" Phillips
// p. 185
package ChapterFive.Tutorial5;
import java.util.Scanner;

public class AssignVolunteer4
{
    public static void main(String[] args) 
    {
        int donationType;
        String volunteer;
        String message;
        final int CLOTHING_CODE = 1;
        final int FURNITURE_CODE = 2;
        final int ELECTRONICS_CODE = 3;
        final int OTHER_CODE = 4;

        final String CLOTHING_PRICER = "Regina";
        final String FURNITURE_PRICER = "Wei";
        final String ELECTRONICS_PRICER = "Lydia";
        final String OTHER_PRICER = "Marco";

        Scanner input = new Scanner(System.in);

        System.out.printf( 
        "***** Donation Options ***** \n\n" +
        "Type %n" +
        "1 for Clothing, %n" +
        "2 for Furniture, %n" + 
        "3 for Electronics, %n" + 
        "or 4 for Other \n\n");
        System.out.print("What type of donation is this? ");
            
        if (input.hasNextInt()) 
        {  // Check if the input is an integer
            donationType = input.nextInt();  // Capture the integer input
            switch(donationType)
            {
                case(CLOTHING_CODE):
                {
                    volunteer = CLOTHING_PRICER;
                    message = "a clothing donation";
                    break;
                }
                case(FURNITURE_CODE) :
                {
                    volunteer = FURNITURE_PRICER;
                    message = "a furniture donation";
                    break;
                }
                case(ELECTRONICS_CODE) :
                {
                    volunteer = ELECTRONICS_PRICER;
                    message = "an electronics donation";
                    break;
                }
                case(OTHER_CODE) :
                {
                    volunteer = OTHER_PRICER;
                    message = "another donation type";
                    break;
                }
                default:
                {
                    volunteer = "invalid";
                    message = "an invalid donation type";
                }
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


