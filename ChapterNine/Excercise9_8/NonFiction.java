// Charles "Nuke" Phillips
// Excercise 8b, p. 387

package ChapterNine.Excercise9_8;

public class NonFiction extends Book
{
    public NonFiction(String title)
    {
        super(title);
        setPrice();
    }
    @Override
    public void setPrice()
    {
        price = 37.99;
    }
}