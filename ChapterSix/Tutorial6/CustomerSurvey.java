// Charles "Nuke" Phillips
// p. 209
package ChapterSix.Tutorial6;
import java.util.*;
public class CustomerSurvey 
{
    public static void main(String[] args) 
    {
        int rating;
        final int MIN = 1;
        final int MAX = 5;
        Scanner input = new Scanner(System.in);
        System.out.printf("Please enter a value that %n" +
        "represents your satisfaction with %n" +
        "our service %n" +
        "Enter a value between " + MIN + "%n" +
        "and " + MAX + "%n" + 
        "with " + MAX + " meaning highly%n" +
        "satisfied and " +
        MIN + " meaning not at all satisfied.");
        System.out.print("\n\nEnter your rating >> ");
        rating = input.nextInt();
        while(rating < MIN || rating > MAX)
        {
            System.out.printf("You must enter a value %n" +
            "between " + MIN + " and " + MAX + "%n");
            System.out.print("Please try again >> ");
            rating = input.nextInt();
        }
        System.out.println("Thank you.");
    }
}
