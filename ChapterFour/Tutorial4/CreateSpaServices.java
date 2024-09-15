// Charles "Nuke" Phillips
// p. 133
package ChapterFour.Tutorial4;
import java.util.Scanner;

public class CreateSpaServices 
{
    public static void main(String[] args) {
        SpaService firstService = new SpaService();
        SpaService secondService = new SpaService();
        SpaService thirdService = new SpaService("facial", 22.99);
        firstService = getData(firstService);
        // secondService = getData(secondService);
        System.out.printf("First service details: %s $ %.2f%n", firstService.getServiceDescription(), firstService.getPrice());
        System.out.printf("Second service details: %s $ %.2f%n", secondService.getServiceDescription(), secondService.getPrice());
        System.out.printf("Third service details: %s $ %.2f%n", thirdService.getServiceDescription(), thirdService.getPrice());
    }
    public static SpaService getData(SpaService service)
    {
        String serviceDescription;
        double price;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter the service >> ");
        serviceDescription = input.nextLine();
        System.out.printf("Enter the price for a(n) %s>> ", serviceDescription);
        price = input.nextDouble();
        input.nextLine();
        service.setServiceDescription(serviceDescription);
        service.setPrice(price);
        return service;
    }
}