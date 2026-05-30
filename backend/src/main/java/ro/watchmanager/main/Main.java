package ro.watchmanager.main;

import ro.watchmanager.model.*;
import ro.watchmanager.service.ShopService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ShopService service = new ShopService();

        
        Brand b1 = new Brand("Rolex", "Switzerland");
        Brand b2 = new Brand("Apple", "USA");
        Strap c1 = new Strap("Steel", 20);
        Strap c2 = new Strap("Silicon", 22);

        MechanicalWatch rolex = new MechanicalWatch("C1", b1, "Submariner", 45000.0, 5, c1, "watches/r_sub.webp", MechanismType.AUTOMATIC, 48);
        Smartwatch appleW = new Smartwatch("C2", b2, "Watch Series 9", 2500.0, 20, c2, "watches/smart.webp", "watchOS", 300);
        
        service.addWatch(rolex);
        service.addWatch(appleW);

        
        Client client1 = new Client("john@test.com", "John Doe", "0722222222");
        service.registerClient(client1);

        
        System.out.println("=== After addition (Apple Watch will be shown first, having lower price) ===");
        service.displaySortedWatches();

        
        System.out.println("\n=== Filtering Rolex ===");
        service.filterByBrand("Rolex");

        
        service.updateWatch("C2", 15, 2400.0);
        System.out.println("\n=== After updating Apple Watch to price 2400 ===");
        service.displaySortedWatches();

        
        service.addReview("C1", new Review(Rating.FIVE, "Exceptional!"));

        
        try {
            Order order1 = new Order("CMD1", client1, LocalDate.now());
            order1.addWatch(rolex);
            service.placeOrder(order1);
        } catch (Exception e) {
            System.err.println("Error placing order: " + e.getMessage());
        }

        
        System.out.println("\n=== History for customer john@test.com ===");
        service.displayClientOrderHistory("john@test.com");

        
        System.out.println("\n=== Order value today ===");
        System.out.println(service.calculateTotalOrderValuePerDay(LocalDate.now()) + " RON");

        
        service.deleteWatch("C2");
        System.out.println("\n=== After deleting C2 ===");
        service.displaySortedWatches();
    }
}
