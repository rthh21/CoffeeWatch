package ro.watchmanager.model;

public class Voucher {
    private String code;
    private double value;

    public Voucher(String code, double value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
}
