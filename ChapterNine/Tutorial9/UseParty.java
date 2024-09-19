// Charles "Nuke" Phillips
// p.333
package ChapterNine.Tutorial9;
import java.util.*;
public class UseParty 
{
    public static void main(String[] args) 
    {
        int guests;
        Party aParty = new Party();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter number of guests for the party >> ");
        guests = input.nextInt();
        aParty.setGuests(guests);
        System.out.println("The party has " +
        aParty.getGuests() + " guests");
        aParty.displayInvitation();
    }
}
