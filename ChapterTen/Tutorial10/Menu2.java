package ChapterTen.Tutorial10;

import javax.swing.JOptionPane;

public class Menu2 
{
    protected String[] entreeChoices = { "Rosemary Chicken",
        "Beef Wellington", "Maine Lobster" };
    private String menu = "";
    private int choice;
    protected char initials[] = new char[entreeChoices.length];

    public String displayMenu() throws MenuException {
        for (int x = 0; x < entreeChoices.length; ++x) 
        {
            menu = menu + "\n" + (x + 1) + " for " +
                entreeChoices[x];
            initials[x] = entreeChoices[x].charAt(0);
        }
        String input = JOptionPane.showInputDialog(null,
        "Type your selection, then press Enter" + menu);
        for (int y = 0; y < entreeChoices.length; ++y) 
            if (input.charAt(0) == initials[y])
                throw (new MenuException(entreeChoices[y]));
        choice = Integer.parseInt(input);
        return (entreeChoices[choice - 1]);
    }
}