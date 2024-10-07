// Charles "Nuke" Phillips
// p.344 figure 9-10
package ChapterNine.Tutorial9;

public class Customer
{
   private int idNumber;
   private double balanceOwed;
   public Customer(int id, double bal)
   {
      idNumber = id;
      balanceOwed = bal;
   }
   public void display()
   {
      System.out.println("Customer #" + idNumber +
         " Balance $" + balanceOwed);
   }
}
