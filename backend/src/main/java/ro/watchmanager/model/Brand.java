package ro.watchmanager.model;

import java.util.Objects;

public class Brand {
    private String nume;
    private String taraOrigine;

    public Brand(String nume, String taraOrigine) {
        this.nume = nume;
        this.taraOrigine = taraOrigine;
    }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getTaraOrigine() { return taraOrigine; }
    public void setTaraOrigine(String taraOrigine) { this.taraOrigine = taraOrigine; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(nume, brand.nume) && Objects.equals(taraOrigine, brand.taraOrigine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nume, taraOrigine);
    }

    @Override
    public String toString() {
        return nume + " (" + taraOrigine + ")";
    }
}
