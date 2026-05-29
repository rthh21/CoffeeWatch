package ro.watchmanager.model;

public class ClientBuilder {
    private String email;
    private String name;
    private String phoneNumber;

    public ClientBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Client build() {
        return new Client(email, name, phoneNumber);
    }
}
