// Charles "Nuke" Phillips
// p. 305
package ChapterEight.Tutorial8;
import java.util.Scanner;
public class TwoDimensionalArrayDemo 
{
    public static void main(String[] args) {
        int [] [] count = new int [3] [3];
        Scanner input = new Scanner(System.in);
        int row, column;
        final int QUIT = 99;
        System.out.print("Enter row or " + QUIT + " to quit > ");
        row = input.nextInt();
        while (row != QUIT) 
        {
            System.out.print("Enter a column >> ");
            column = input.nextInt();
            if(row < count.length && column < count[row].length)
            {
                count[row] [column]++;
                for(int r = 0; r < count.length; ++r)
                {
                    for(int c = 0; c < count[r].length; ++c)
                    {
                        System.out.print(count[r] [c] + " ");
                    }
                    System.out.println();
                }
            }
            else
            {
                System.out.println("Invalid position selected");
            }
            System.out.print("Enter row or " + QUIT + " to quit > ");
            row = input.nextInt();
        }
    }
}
