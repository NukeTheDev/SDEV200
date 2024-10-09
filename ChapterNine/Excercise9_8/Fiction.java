// Charles "Nuke" Phillips
// Excercise 8b, p. 387
package ChapterNine.Excercise9_8;

public class Fiction extends Book
{
    public Fiction(String title)
    {
        super(title);
        setPrice();
    }
    @Override
    public void setPrice()
    {
        price = 24.99;
    }
}