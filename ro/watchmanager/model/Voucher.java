package ro.watchmanager.model;

public class Voucher {
    private String cod;
    private double valoare;

    public Voucher(String cod, double valoare) {
        this.cod = cod;
        this.valoare = valoare;
    }

    public String getCod() { return cod; }
    public void setCod(String cod) { this.cod = cod; }

    public double getValoare() { return valoare; }
    public void setValoare(double valoare) { this.valoare = valoare; }
}
