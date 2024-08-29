// Charles "Nuke" Phillips
// p 81 Excercise 8a
package ChapterTwo.Excercise2;

import java.util.Scanner;

public class ChiliToGo 
{

   public static void main(String[] args)
   {
        int adultQty;
        int childQty;
        double adultPrice = 7.00;
        double childPrice = 4.00;
        double adultTotal;
        double childTotal;
        double grandTotal;
        Scanner inputDevice = new Scanner(System.in);
        System.out.print("Please enter the number of adult meals >> ");
        adultQty = inputDevice.nextInt();
        System.out.print("Please enter the number of child meals >> ");
        childQty = inputDevice.nextInt();
        adultTotal = adultQty * adultPrice;
        childTotal = childQty * childPrice;
        grandTotal = adultTotal + childTotal;

        // Format the results string with inline pluralization logic
        String results = String.format(
            "====Bill====%n" +
            "%d adult meal%s %n $%.2f%n" +
            "%d kid's meal%s %n $%.2f%n" +
            "total: $%.2f",
            adultQty, (adultQty > 1) ? "s" : "",
            adultTotal,
            childQty, (childQty > 1) ? "s" : "",
            childTotal,
            grandTotal
        );

        // Print the result
        System.out.println(results);
        inputDevice.close();
   }
}