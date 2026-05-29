package ro.watchmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderId;
    private Client client;
    private List<Watch> watches;
    private LocalDate orderDate;
    private double totalValue;

    public Order(String orderId, Client client, LocalDate orderDate) {
        this.orderId = orderId;
        this.client = client;
        this.orderDate = orderDate;
        this.watches = new ArrayList<>();
        this.totalValue = 0;
    }

    public void addWatch(Watch watch) {
        this.watches.add(watch);
        this.totalValue += watch.getPrice();
    }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Watch> getWatches() { return watches; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public double getTotalValue() { return totalValue; }

    @Override
    public String toString() {
        return "Order " + orderId + " (Date: " + orderDate + ") | Client: " + client.getName() + " | Value: " + totalValue + " RON | Products: " + watches.size();
    }
}
