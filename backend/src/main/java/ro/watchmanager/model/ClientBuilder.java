package ro.watchmanager.model;

public class ClientBuilder {
    private String email;
    private String nume;
    private String telefon;

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setNume(String nume) {
        this.nume = nume;
        return this;
    }

    public ClientBuilder setTelefon(String telefon) {
        this.telefon = telefon;
        return this;
    }

    public Client build() {
        return new Client(email, nume, telefon);
    }
}
