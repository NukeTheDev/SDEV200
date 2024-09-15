// Charles "Nuke" Phillips
//p. 138
package ChapterFour.Tutorial4;

public class SpaService {
    private String serviceDescription;
    private double price;

    public SpaService()
    {
        this("XXX", 0);
    }
    public SpaService(String desc, double pr)
    {
        serviceDescription = desc;
        price = pr;
    }
    public void setServiceDescription(String service)
    {
        serviceDescription = service;
    }
    public void setPrice(double servicePrice)
    {
        price = servicePrice;
    }
    public String getServiceDescription()
    {
        return serviceDescription;
    }
    public double getPrice()
    {
        return price;
    }
}