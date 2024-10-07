// Charles "Nuke" Phillips
// p. 344 Figure 9-11
package ChapterNine.Tutorial9;

public class PreferredCustomer extends Customer
{
   double discountRate;
   public PreferredCustomer(int id, double bal, double rate)
   {
      super(id, bal);
      discountRate = rate;
   }
   @Override
   public void display()
   {
      super.display();
      System.out.println("   Discount rate is " + discountRate);
   }
}