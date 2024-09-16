// Charles "Nuke" Phillips
//p. 156
package ChapterFour.Excercise4;

public class Sandwich 
{
    private String mainIngredient;
    private String breadType;
    private double price;

    // Set methods
    public void setMainIngredient(String mainIngredient) 
    {
        this.mainIngredient = mainIngredient;
    }

    public void setBreadType(String breadType) 
    {
        this.breadType = breadType;
    }

    public void setPrice(double price) 
    {
        this.price = price;
    }

    // Getters
    public String getMainIngredient() 
    {
        return mainIngredient;
    }

    public String getBreadType() 
    {
        return breadType;
    }

    public double getPrice() 
    {
        return price;
    }
}