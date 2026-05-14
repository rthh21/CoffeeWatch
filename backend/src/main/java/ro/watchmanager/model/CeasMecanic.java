package ro.watchmanager.model;

public class CeasMecanic extends Ceas {
    private TipMecanism tipMecanism; // AUTOMAT, MANUAL
    private int rezervaPutereOre;

    public CeasMecanic(String id, Brand brand, String numeModel, double pret, int stoc, Curea curea, TipMecanism tipMecanism, int rezervaPutereOre) {
        super(id, brand, numeModel, pret, stoc, curea);
        this.tipMecanism = tipMecanism;
        this.rezervaPutereOre = rezervaPutereOre;
    }

    public TipMecanism getTipMecanism() { return tipMecanism; }
    public void setTipMecanism(TipMecanism tipMecanism) { this.tipMecanism = tipMecanism; }

    public int getRezervaPutereOre() { return rezervaPutereOre; }
    public void setRezervaPutereOre(int rezervaPutereOre) { this.rezervaPutereOre = rezervaPutereOre; }

    @Override
    public String toString() {
        return "[Mecanic] " + super.toString() + " | Mecanism: " + tipMecanism + " | Rezerva: " + rezervaPutereOre + "h";
    }
}
