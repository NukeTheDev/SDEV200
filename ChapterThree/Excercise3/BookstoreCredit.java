// Charles "Nuke" Phillips
// p. 112
package ChapterThree.Excercise3;
import java.util.Scanner;

public class BookstoreCredit {
    public static void main(String[] args) 
    {
        String name;
        double gpa;
        // Displays the program header at program start
        System.out.printf("********************Hermosa Highschool Promo********************%n" +
        "As an incentive to earning good grades this year, %n" +
        "We have decided to award students a book store credit 10x their GPA. %n" +
        "For example, if your GPA is 3.0, they will earn a $30 credit \n\n"); // \n\n used to create input box on a new line
        Scanner input = new Scanner(System.in);
       
        // Name input and validation
        System.out.print("Enter student's name >> ");
        while (true) {
            name = input.next();
            if (name.matches("[a-zA-Z]+")) { // Check if name contains only letters
                break;
            } else {
                System.out.printf("Invalid Name! Please enter a valid student name below >> ");
            }
        }
        input.nextLine(); // Used to consume the enter key character left over in the input buffer

        // GPA input and validation
        System.out.printf("Enter %s's GPA below >> ", name);
        while (true) {
            if (input.hasNextDouble()) { // Check if the input is a number
                gpa = input.nextDouble();
                if (gpa >= 0.0 && gpa <= 4.0) { // Check if GPA is within valid range
                    break;
                } else {
                    System.out.printf("%nInvalid GPA! Please enter a valid GPA (2.0 - 4.0) below >> ");
                }
            } else {
                System.out.printf("%nInvalid GPA! Please enter a valid GPA (2.0 - 4.0) below >> ");
                input.next(); // Consume the invalid input
            }
        }

        // Display the bookstore credit based on GPA
        displayBookstoreCredit(name, gpa);

        // Close the input stream
        input.close();
    }
    // Logic for computing GPA ranks
    public static String computeGPARank(double GPA) {
        String gpa_rank;
    
        if (GPA < 2.0) {
            gpa_rank = "Failing";
        } else if (GPA >= 2.0 && GPA <= 2.49) {
            gpa_rank = "Below Average";
        } else if (GPA >= 2.5 && GPA <= 2.99) {
            gpa_rank = "Average";
        } else if (GPA >= 3.0 && GPA <= 3.49) {
            gpa_rank = "Above Average";
        } else if (GPA >= 3.5 && GPA <= 3.74) {
            gpa_rank = "Honor Roll";
        } else if (GPA >= 3.75 && GPA <= 3.99) {
            gpa_rank = "High Honor Roll";
        } else if (GPA == 4.0) {
            gpa_rank = "Principal's Honor Roll";
        } else {
            gpa_rank = "Invalid GPA";
        }
        return gpa_rank;
    }

     // Method to display the bookstore credit
     public static void displayBookstoreCredit(String name, double GPA) {
        String gpa_rank = computeGPARank(GPA);
        double credit = GPA * 10;

        String results = String.format(
            "%n----------Award Letter----------" +
            "%nCongratulations %s!%n" +
            "You have been awarded a $%.2f%nbookstore credit based on your %s GPA.",
            name, credit, gpa_rank);

        if (gpa_rank.equals("Failing")) { // Display warning if GPA is too low
            results += String.format("%nWarning! work very hard to get your grades up!");
        }

        // Print the result
        System.out.println(results);
    }
}