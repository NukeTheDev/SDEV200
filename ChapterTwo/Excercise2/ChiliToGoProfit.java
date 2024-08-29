package ChapterTwo.Excercise2;

import java.util.Scanner;

public class ChiliToGoProfit
{

   public static void main(String[] args)
   {
        int adultQty;
        int childQty;
        double adultPrice = 7.00;
        double childPrice = 4.00;
        double adultCost = 4.35;
        double childCost = 3.10;
        double adultProfit;
        double childProfit;
        double totalProfit;
        Scanner inputDevice = new Scanner(System.in);
        System.out.print("Please enter the number of adult meals >> ");
        adultQty = inputDevice.nextInt();
        System.out.print("Please enter the number of child meals >> ");
        childQty = inputDevice.nextInt();
        adultProfit = ((adultPrice - adultCost) * adultQty);
        childProfit = ((childPrice - childCost) * childQty);
        totalProfit = adultProfit + childProfit;

        // Format the results string with inline pluralization logic
        String results = String.format(
            "------Profit------%n" +
            "Profit for %d adult meal%s %n $%.2f%n" +
            "Profit for %d child meal%s %n $%.2f%n" +
            "Total profit made: $%.2f",
            adultQty, (adultQty > 1) ? "s" : "",
            adultProfit,
            childQty, (childQty > 1) ? "s" : "",
            childProfit,
            totalProfit
        );

        // Print the result
        System.out.println(results);
        inputDevice.close();
   }
}