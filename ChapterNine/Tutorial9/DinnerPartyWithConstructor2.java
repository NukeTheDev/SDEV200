// Charles "Nuke" Phillips
// p.333

package ChapterNine.Tutorial9;

public class DinnerPartyWithConstructor2 extends PartyWithConstructor2
{
    private int dinnerChoice;
    public DinnerPartyWithConstructor2(int numGuests)
    {
        super(numGuests);
    }
    public int getDinnerChoice()
    {
        return dinnerChoice;
    }
    public void setDinnerChoice(int choice)
    {
        dinnerChoice = choice;
    }
    @Override
    public void displayInvitation()
    {
        System.out.println("Please come to my dinner party");
    }
}
