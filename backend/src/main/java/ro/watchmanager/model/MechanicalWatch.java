package ro.watchmanager.model;

public class MechanicalWatch extends Watch {
    private MechanismType mechanismType; // AUTOMATIC, MANUAL
    private int powerReserveHours;

    public MechanicalWatch(String id, Brand brand, String modelName, double price, int stock, Strap strap, MechanismType mechanismType, int powerReserveHours) {
        super(id, brand, modelName, price, stock, strap);
        this.mechanismType = mechanismType;
        this.powerReserveHours = powerReserveHours;
    }

    public MechanismType getMechanismType() { return mechanismType; }
    public void setMechanismType(MechanismType mechanismType) { this.mechanismType = mechanismType; }

    public int getPowerReserveHours() { return powerReserveHours; }
    public void setPowerReserveHours(int powerReserveHours) { this.powerReserveHours = powerReserveHours; }

    @Override
    public String toString() {
        return "[Mechanical] " + super.toString() + " | Mechanism: " + mechanismType + " | Reserve: " + powerReserveHours + "h";
    }
}
