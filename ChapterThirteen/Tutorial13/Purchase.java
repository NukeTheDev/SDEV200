package ChapterThirteen.Tutorial13;


public class Purchase implements Comparable<Purchase>
{
    private String category;
    private double price;
    public Purchase(String cat, double pr)
    {
        category = cat;
        price = pr;
    }
    public String getCategory()
    {
        return category;
    }
    public double getPrice()
    {
        return price;
    }
    @Override
    public int compareTo(Purchase p)
    {
        int result;
        result = category.compareTo(p.getCategory());
        return result;
    }
}