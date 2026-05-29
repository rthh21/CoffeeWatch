package ro.watchmanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Watch implements Comparable<Watch>, Discountable {
    private String id;
    private Brand brand;
    private String modelName;
    private double price;
    private int stock;
    private Strap strap;
    private List<Review> reviews;

    public Watch(String id, Brand brand, String modelName, double price, int stock, Strap strap) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.price = price;
        this.stock = stock;
        this.strap = strap;
        this.reviews = new ArrayList<>();
    }

    @Override
    public void applyDiscount(double percentage) {
        this.price -= (this.price * percentage / 100);
    }

    public void addReview(Review r) {
        this.reviews.add(r);
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Strap getStrap() { return strap; }
    public void setStrap(Strap strap) { this.strap = strap; }

    public List<Review> getReviews() { return reviews; }

    @Override
    public int compareTo(Watch otherWatch) {
        // Sort by price (ascending). If equal, by ID to avoid overwriting in TreeSet
        int result = Double.compare(this.price, otherWatch.price);
        if (result == 0) {
            return this.id.compareTo(otherWatch.id);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Watch watch = (Watch) o;
        return Objects.equals(id, watch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return brand.getName() + " " + modelName + " (Price: " + price + " RON, Stock: " + stock + " pcs)";
    }
}
