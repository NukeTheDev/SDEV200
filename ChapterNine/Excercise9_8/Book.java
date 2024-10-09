// Charles "Nuke" Phillips
// Excercise 8a, p. 387

package ChapterNine.Excercise9_8;

public abstract class Book
{
    String title;
    double price;
    public Book(String title)
    {
        this.title = title;
    }
    public String getTitle()
    {
        return title;
    }
    public double getPrice()
    {
        return price;
    }
    public abstract void setPrice();
}