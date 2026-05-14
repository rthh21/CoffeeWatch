package ro.watchmanager.model;

public class CeasMecanic extends Ceas {
    private String tipMecanism; // automat, manual
    private int rezervaPutereOre;

    public CeasMecanic(String id, Brand brand, String numeModel, double pret, int stoc, Curea curea, String tipMecanism, int rezervaPutereOre) {
        super(id, brand, numeModel, pret, stoc, curea);
        this.tipMecanism = tipMecanism;
        this.rezervaPutereOre = rezervaPutereOre;
    }

    public String getTipMecanism() { return tipMecanism; }
    public void setTipMecanism(String tipMecanism) { this.tipMecanism = tipMecanism; }

    public int getRezervaPutereOre() { return rezervaPutereOre; }
    public void setRezervaPutereOre(int rezervaPutereOre) { this.rezervaPutereOre = rezervaPutereOre; }

    @Override
    public String toString() {
        return "[Mecanic] " + super.toString() + " | Mecanism: " + tipMecanism + " | Rezerva: " + rezervaPutereOre + "h";
    }
}
