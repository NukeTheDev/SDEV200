// Charles "Nuke" Phillips
// p. 375

package ChapterNine.Tutorial9_2;

import javax.swing.JOptionPane;

public class InsuredCarDemo 
{
    public static void main(String[] args) 
    {
        InsuredCar myCar = new InsuredCar();
        JOptionPane.showMessageDialog(null, myCar.toString());
    }
}
