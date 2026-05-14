package ro.watchmanager.model;

public class Curea {
    private String material;
    private int dimensiuneInMm;

    public Curea(String material, int dimensiuneInMm) {
        this.material = material;
        this.dimensiuneInMm = dimensiuneInMm;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public int getDimensiuneInMm() { return dimensiuneInMm; }
    public void setDimensiuneInMm(int dimensiuneInMm) { this.dimensiuneInMm = dimensiuneInMm; }

    @Override
    public String toString() {
        return "Curea " + material + " de " + dimensiuneInMm + "mm";
    }
}
