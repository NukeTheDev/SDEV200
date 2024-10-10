// Charles "Nuke" Phillips
// p. 407

package ChapterTen.Tutorial10;

import javax.swing.JOptionPane;

public class ExceptionDemo2
{
    public static void main(String[] args) 
    {
        int numerator = 0, denominator = 0, result = 0;
        String inputString;
        try
        {
            inputString = JOptionPane.showInputDialog(null,
            "Enter a number to be divided");
            numerator = Integer.parseInt(inputString);
            inputString = JOptionPane.showInputDialog(null,
            "Enter a number to divide into the first number");
            denominator = Integer.parseInt(inputString);
            
            // Check for division by zero
            if (denominator == 0) {
                // Display warning for division by zero
                JOptionPane.showMessageDialog(null, 
                "Division by zero is undefined or results in NaN.");
            } else {
                // Perform the division if the denominator is not equal to zero
                result = numerator / denominator;
            }
        }
        catch(ArithmeticException e)
        {
            JOptionPane.showMessageDialog(null, 
            e.getMessage());
        }
        catch(NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(null, 
            "This application accepts digits only!");
            numerator = 999;
            denominator = 999;
            result = 1;
        }

        // Display the result if the division was performed
        if (denominator != 0) {
            JOptionPane.showMessageDialog(null, numerator + " / " +  
            denominator + "\nResult is " + result);
        }
    }
}