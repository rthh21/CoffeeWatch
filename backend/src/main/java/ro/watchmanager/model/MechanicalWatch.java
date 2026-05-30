package ro.watchmanager.model;

public class MechanicalWatch extends Watch {
    private MechanismType mechanismType; 
    private int powerReserveHours;

    public MechanicalWatch(String id, Brand brand, String modelName, double price, int stock, Strap strap, String imageUrl, MechanismType mechanismType, int powerReserveHours) {
        super(id, brand, modelName, price, stock, strap, imageUrl);
        this.mechanismType = mechanismType;
        this.powerReserveHours = powerReserveHours;
    }

    public MechanismType getMechanismType() { return mechanismType; }
    public void setMechanismType(MechanismType mechanismType) { this.mechanismType = mechanismType; }

    public int getPowerReserveHours() { return powerReserveHours; }
    public void setPowerReserveHours(int powerReserveHours) { this.powerReserveHours = powerReserveHours; }

    public String getType() { return "Mechanical"; }

    @Override
    public String toString() {
        return "[Mechanical] " + super.toString() + " | Mechanism: " + mechanismType + " | Reserve: " + powerReserveHours + "h";
    }
}
