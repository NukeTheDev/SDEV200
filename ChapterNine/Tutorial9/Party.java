// Charles "Nuke" Phillips
// p.333
package ChapterNine.Tutorial9;

public class Party 
{
    private int guests;

    public int getGuests()
    {
        return guests;
    }
    public void setGuests(int numGuests)
    {
        guests = numGuests;
    }
    public void displayInvitation()
    {
        System.out.println("Please come to my party");
    }
}