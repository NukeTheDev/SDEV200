// Charles "Nuke" Phillips
// p. 399 Figure 10-37

package ChapterTen.Tutorial10;

public class HighBalanceException extends Exception
{
   public HighBalanceException()
   {
      super("Customer balance is high");
   }
}