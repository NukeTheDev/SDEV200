// Charles "Nuke" Phillips
// p. 431
package ChapterTen.Tutorial10;

import java.io.IOException;
import java.util.Scanner;

public class CalculatorDemo
{
    public static void main(String[] args) throws IOException
    {
        Scanner input = new Scanner(System.in);
        // Because I am using Docker WSL2 container, I was unable to run windows utilities in linux 
        //so I had to run it locally outside of the container for this particular program.
        Process process = Runtime.getRuntime().exec("cmd /c C:\\Windows\\System32\\calc.exe");


        double num1 = 279.6; 
        double num2 = 872.8;
        double answer = num1 + num2;
        double usersAnswer;
        System.out.print("What is the sum of " + num1 +
        " and " + num2 + "? >> ");
        usersAnswer = input.nextDouble();
        // used to see if the user's answer is equal to the correct answer.
        if(usersAnswer == answer)
            System.out.println("Correct!");
        else
            System.out.println("Sorry - the answer is " + answer);
    }
}