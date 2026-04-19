package ro.watchmanager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Ceas implements Comparable<Ceas> {
    private String id;
    private Brand brand;
    private String numeModel;
    private double pret;
    private int stoc;
    private Curea curea;
    private List<Recenzie> recenzii;

    public Ceas(String id, Brand brand, String numeModel, double pret, int stoc, Curea curea) {
        this.id = id;
        this.brand = brand;
        this.numeModel = numeModel;
        this.pret = pret;
        this.stoc = stoc;
        this.curea = curea;
        this.recenzii = new ArrayList<>();
    }

    public void adaugaRecenzie(Recenzie r) {
        this.recenzii.add(r);
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public String getNumeModel() { return numeModel; }
    public void setNumeModel(String numeModel) { this.numeModel = numeModel; }

    public double getPret() { return pret; }
    public void setPret(double pret) { this.pret = pret; }

    public int getStoc() { return stoc; }
    public void setStoc(int stoc) { this.stoc = stoc; }

    public Curea getCurea() { return curea; }
    public void setCurea(Curea curea) { this.curea = curea; }

    public List<Recenzie> getRecenzii() { return recenzii; }

    @Override
    public int compareTo(Ceas altCeas) {
        // Sortam dupa pret (ascendent). Daca sunt egale, dupa ID pentru a nu le suprascrie in TreeSet
        int result = Double.compare(this.pret, altCeas.pret);
        if (result == 0) {
            return this.id.compareTo(altCeas.id);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ceas ceas = (Ceas) o;
        return Objects.equals(id, ceas.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return brand.getNume() + " " + numeModel + " (Pret: " + pret + " RON, Stoc: " + stoc + " buc)";
    }
}
