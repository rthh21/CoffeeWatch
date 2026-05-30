package ro.watchmanager.service;

import ro.watchmanager.exception.InsufficientStockException;
import ro.watchmanager.model.*;
import java.time.LocalDate;
import java.util.*;

public class ShopService {
    private TreeSet<Watch> watchStock;
    private HashMap<String, Client> clients;
    private List<Order> orderHistory;
    private AuditService auditService;

    public ShopService() {
        this.watchStock = new TreeSet<>();
        this.clients = new HashMap<>();
        this.orderHistory = new ArrayList<>();
        this.auditService = AuditService.getInstance();
    }

    public TreeSet<Watch> getWatchStock() {
        return watchStock;
    }

    public List<Watch> getWatchesByBrand(String brandName) {
        return watchStock.stream()
                .filter(c -> c.getBrand().getName().equalsIgnoreCase(brandName))
                .toList();
    }

    public void addWatch(Watch watch) {
        watchStock.add(watch);
        auditService.logEvent("addWatch");
    }

    public void addBrand(Brand brand) {
        auditService.logEvent("addBrand");
        
        
    }

    public void registerClient(Client client) {
        clients.put(client.getEmail(), client);
        auditService.logEvent("registerClient");
    }

    public void placeOrder(Order order) throws InsufficientStockException {
        auditService.logEvent("placeOrder");
        for (Watch c : order.getWatches()) {
            Optional<Watch> found = watchStock.stream().filter(x -> x.getId().equals(c.getId())).findFirst();
            if (found.isPresent()) {
                Watch watchInStock = found.get();
                if (watchInStock.getStock() <= 0) {
                    throw new InsufficientStockException("Insufficient stock for: " + watchInStock.getModelName());
                }
                watchInStock.setStock(watchInStock.getStock() - 1);
            }
        }
        orderHistory.add(order);
    }

    public void displaySortedWatches() {
        auditService.logEvent("displaySortedWatches");
        watchStock.forEach(System.out::println);
    }

    public void filterByBrand(String brandName) {
        auditService.logEvent("filterByBrand");
        watchStock.stream()
                .filter(c -> c.getBrand().getName().equalsIgnoreCase(brandName))
                .forEach(System.out::println);
    }

    public void updateWatch(String watchId, int newStock, double newPrice) {
        auditService.logEvent("updateWatch");
        watchStock.stream().filter(c -> c.getId().equals(watchId)).findFirst().ifPresent(c -> {
            watchStock.remove(c);
            c.setStock(newStock);
            c.setPrice(newPrice);
            watchStock.add(c);
        });
    }

    public void deleteWatch(String watchId) {
        auditService.logEvent("deleteWatch");
        watchStock.removeIf(c -> c.getId().equals(watchId));
    }

    public void displayClientOrderHistory(String clientEmail) {
        auditService.logEvent("displayClientOrderHistory");
        orderHistory.stream()
                .filter(c -> c.getClient().getEmail().equals(clientEmail))
                .forEach(System.out::println);
    }

    public void addReview(String watchId, Review rec) {
        auditService.logEvent("addReview");
        watchStock.stream().filter(c -> c.getId().equals(watchId)).findFirst().ifPresent(c -> c.addReview(rec));
    }

    public double calculateTotalOrderValuePerDay(LocalDate date) {
        auditService.logEvent("calculateTotalOrderValuePerDay");
        return orderHistory.stream()
                .filter(c -> c.getOrderDate().equals(date))
                .mapToDouble(Order::getTotalValue)
                .sum();
    }

    
    public void applyGeneralDiscount(double percentage) {
        auditService.logEvent("applyGeneralDiscount");
        watchStock.forEach(c -> c.applyDiscount(percentage));
    }

    public List<Watch> getWatchesInLimitedStock(int threshold) {
        auditService.logEvent("getWatchesInLimitedStock");
        return watchStock.stream()
                .filter(c -> c.getStock() < threshold)
                .toList();
    }

    public void deleteClient(String email) {
        auditService.logEvent("deleteClient");
        clients.remove(email);
    }

    public double getAverageOrderValue() {
        auditService.logEvent("getAverageOrderValue");
        return orderHistory.stream()
                .mapToDouble(Order::getTotalValue)
                .average()
                .orElse(0.0);
    }

    public List<Watch> getTopReviewedProducts() {
        auditService.logEvent("getTopReviewedProducts");
        return watchStock.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getReviews().size(), c1.getReviews().size()))
                .toList();
    }
}
