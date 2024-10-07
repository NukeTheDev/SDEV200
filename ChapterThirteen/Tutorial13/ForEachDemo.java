package ChapterThirteen.Tutorial13;
import java.util.*;
public class ForEachDemo 
{
   public static void main(String[] args) 
   {
      LinkedList<Integer> sales = new LinkedList<Integer>();
      sales.add(75);
      sales.add(81);
      sales.add(43);
      sales.add(55);
      for(int amt: sales)
         System.out.print(amt + "  ");
      System.out.println();			
   }	
}