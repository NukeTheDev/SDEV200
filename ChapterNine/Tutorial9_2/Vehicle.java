package ChapterNine.Tutorial9_2;

public abstract class Vehicle
{
    private String powerSource;
    private int wheels;
    protected int price;
    public Vehicle(String powerSource, int wheels)
    {
        setWheels(wheels);
        setPrice();
        setPowerSource(powerSource);
    }
    public String getPowerSource()
    {
        return powerSource;
    }
    public int getWheels()
    {
        return wheels;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPowerSource(String source)
    {
        powerSource = source;
    }
    public void setWheels(int numWheels)
    {
        wheels = numWheels;
    }
    public abstract void setPrice();
}

