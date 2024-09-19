// Charles "Nuke" Phillips
// p. 344 Figure 9-12

package ChapterNine.Tutorial9;

public class TestCustomers
{
   public static void main(String[] args)
   {
      Customer oneCust = new Customer(124, 123.45);
      PreferredCustomer onePCust = new
         PreferredCustomer(125, 3456.78, 0.15);
      oneCust.display();
      onePCust.display();
   }
}
