package ro.watchmanager.model;

public class Smartwatch extends Ceas {
    private String sistemOperare;
    private int capacitateBaterieMah;

    public Smartwatch(String id, Brand brand, String numeModel, double pret, int stoc, Curea curea, String sistemOperare, int capacitateBaterieMah) {
        super(id, brand, numeModel, pret, stoc, curea);
        this.sistemOperare = sistemOperare;
        this.capacitateBaterieMah = capacitateBaterieMah;
    }

    public String getSistemOperare() { return sistemOperare; }
    public void setSistemOperare(String sistemOperare) { this.sistemOperare = sistemOperare; }

    public int getCapacitateBaterieMah() { return capacitateBaterieMah; }
    public void setCapacitateBaterieMah(int capacitateBaterieMah) { this.capacitateBaterieMah = capacitateBaterieMah; }

    @Override
    public String toString() {
        return "[Smartwatch] " + super.toString() + " | OS: " + sistemOperare + " | Baterie: " + capacitateBaterieMah + "mAh";
    }
}
