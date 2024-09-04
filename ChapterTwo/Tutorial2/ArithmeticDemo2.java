// Charles "Nuke" Phillips
// p. 71
package ChapterTwo.Tutorial2;

import java.util.Scanner;

public class ArithmeticDemo2 {
    public static void main(String[] args) {
        double firstNumber;
        double secondNumber;
        double sum;
        double difference;
        double average;
        Scanner input = new Scanner(System.in);
        System.out.print("Please enter an double >> ");
        firstNumber = input.nextDouble();
        System.out.print("Please enter another double >> ");
        secondNumber = input.nextDouble();
        sum = firstNumber + secondNumber;
        difference = firstNumber - secondNumber;
        average = sum / 2;

        String rounded_results = String.format(
            firstNumber + " + " + secondNumber + " is " + "%.2f" + "%n" +
            firstNumber + " - " + secondNumber + " is " + "%.2f" + "%n" +
            "The average of " + firstNumber + " and " + secondNumber + " is " + "%.2f" + "%n",
            sum, difference, average
        );

        System.out.println(rounded_results);
        input.close();
    }
}

