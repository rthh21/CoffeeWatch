package ro.watchmanager.model;

public class Smartwatch extends Watch {
    private String operatingSystem;
    private int batteryCapacityMah;

    public Smartwatch(String id, Brand brand, String modelName, double price, int stock, Strap strap, String imageUrl, String operatingSystem, int batteryCapacityMah) {
        super(id, brand, modelName, price, stock, strap, imageUrl);
        this.operatingSystem = operatingSystem;
        this.batteryCapacityMah = batteryCapacityMah;
    }

    public String getOperatingSystem() { return operatingSystem; }
    public void setOperatingSystem(String operatingSystem) { this.operatingSystem = operatingSystem; }

    public int getBatteryCapacityMah() { return batteryCapacityMah; }
    public void setBatteryCapacityMah(int batteryCapacityMah) { this.batteryCapacityMah = batteryCapacityMah; }

    public String getType() { return "Smartwatch"; }

    @Override
    public String toString() {
        return "[Smartwatch] " + super.toString() + " | OS: " + operatingSystem + " | Battery: " + batteryCapacityMah + "mAh";
    }
}
