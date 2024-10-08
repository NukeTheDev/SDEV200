package ChapterTen.Tutorial10;

public class VegetarianMenu extends Menu
{
    String [] vegEntreeChoices = {"Spinach Lasagna",
        "Cheese Enchiladas", "Fruit Plate"};
    public VegetarianMenu()
    {
        super();
        for (int x = 0; x < vegEntreeChoices.length; ++x)
            entreeChoices[x] = vegEntreeChoices[x];
    }
}