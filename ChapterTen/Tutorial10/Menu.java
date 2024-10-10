// Charles "Nuke" Phillips
// p. 424
package ChapterTen.Tutorial10;

import javax.swing.*;

public class Menu 
{
    protected String[] entreeChoices = {"Rosemary Chicken", "Beef Wellington", "Maine Lobster"};
    private String menu = "";
    private int choice;
    public String displayMenu()
    {
        for(int x=0; x < entreeChoices.length; ++x)
        {
            menu = menu + "\n" + (x + 1) + " for " +
                entreeChoices[x];
        }
        String input = JOptionPane.showInputDialog(null,
            "Type your selection, then press Enter." + menu);
        choice = Integer.parseInt(input);
        return(entreeChoices[choice - 1]);
    }
}