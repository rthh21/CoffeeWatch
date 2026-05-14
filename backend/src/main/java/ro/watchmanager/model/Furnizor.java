package ro.watchmanager.model;

public class Furnizor {
    private int id;
    private String nume;
    private String contact;

    public Furnizor(int id, String nume, String contact) {
        this.id = id;
        this.nume = nume;
        this.contact = contact;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}
