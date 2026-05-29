package ro.watchmanager.model;

public class Strap {
    private String material;
    private int sizeInMm;

    public Strap(String material, int sizeInMm) {
        this.material = material;
        this.sizeInMm = sizeInMm;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    public int getSizeInMm() { return sizeInMm; }
    public void setSizeInMm(int sizeInMm) { this.sizeInMm = sizeInMm; }

    @Override
    public String toString() {
        return "Strap " + material + ", " + sizeInMm + "mm";
    }
}
