package ro.watchmanager.model;

import java.util.Objects;

public class Brand {
    private String name;
    private String countryOfOrigin;

    public Brand(String name, String countryOfOrigin) {
        this.name = name;
        this.countryOfOrigin = countryOfOrigin;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCountryOfOrigin() { return countryOfOrigin; }
    public void setCountryOfOrigin(String countryOfOrigin) { this.countryOfOrigin = countryOfOrigin; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(name, brand.name) && Objects.equals(countryOfOrigin, brand.countryOfOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, countryOfOrigin);
    }

    @Override
    public String toString() {
        return name + " (" + countryOfOrigin + ")";
    }
}
