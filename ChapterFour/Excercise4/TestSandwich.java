// Charles "Nuke" Phillips
// p. 156
package ChapterFour.Excercise4;
import java.util.Scanner;

public class TestSandwich
{
    public static void main(String[] args) 
    {
        String mainIngredient;
        String breadType;
        double price;

        Sandwich sandwich = new Sandwich();
        
        Scanner input = new Scanner(System.in);
        System.out.print("Enter main filling for your sandwich >> ");
        mainIngredient = input.nextLine();
        System.out.print("Enter the bread type for your sandwich >> ");
        breadType = input.nextLine();
        System.out.print("Enter the price >> ");
        price = input.nextDouble();
        input.nextLine();
        sandwich.setMainIngredient(mainIngredient);
        sandwich.setBreadType(breadType);
        sandwich.setPrice(price);

        System.out.printf(
            "---------- Order Details ---------- %n" + 
            "Main Ingredient: %s %n" +
            "Bread Type: %s %n" +
            "Price: $ %.2f %n", sandwich.getMainIngredient(), sandwich.getBreadType(), sandwich.getPrice());
    }
}