package ro.watchmanager.model;

import java.util.Objects;

public class Client {
    private String email;
    private String nume;
    private String telefon;

    public Client(String email, String nume, String telefon) {
        this.email = email;
        this.nume = nume;
        this.telefon = telefon;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(email, client.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return nume + " (" + email + ", " + telefon + ")";
    }
}
