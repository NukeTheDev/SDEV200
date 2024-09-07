// Charles "Nuke" Phillips
// p. 112
package ChapterThree.Excercise3;
import java.util.Scanner;

public class BookstoreCredit {
    public static void main(String[] args) 
    {
        double gpa;
        double credit;
        String name;
        Scanner input = new Scanner(System.in);
        programHeader(); // Displays the program header at program start
        System.out.print("Enter your GPA below >> ");
        gpa = input.nextDouble();
        input.nextLine(); // Used to consume the enter key character leftover in the input buffer.
        System.out.print("Enter your name >> ");
        name = input.next();
        credit = computeBookstoreCredit(gpa);

        // Format the results string with inline pluralization logic
        String results = String.format(
            "----------Award Letter----------" +
            "\n\n Congratulations %s, \n\n You have been awarded %n" +
            "$%.2f in book store credit",
            name, credit);

        // Print the result
        System.out.println(results);
        input.close();
    }
    public static void programHeader()
    {
        System.out.printf("********************Hermosa Highschool Promo********************%n" +
        "As an incentive to earning good grades this year, %n" +
        "We have decided to award students a book store credit 10x their GPA. %n" +
        "For example, if your GPA is 3.0, you'll earn a $30 credit \n\n"); // \n\n used to create input box on a new line
    }
    public static double computeBookstoreCredit(double GPA)
    {
        double credit;
        credit = GPA * 10;
        return credit;
    }
}