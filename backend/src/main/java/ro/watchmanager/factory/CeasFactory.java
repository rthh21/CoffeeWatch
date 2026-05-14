package ro.watchmanager.factory;

import ro.watchmanager.model.*;

public class CeasFactory {
    public static Ceas creeazaCeas(String tip, String id, Brand brand, String model, double pret, int stoc, Curea curea, String extraStr, int extraInt) {
        if (tip.equalsIgnoreCase("Mecanic")) {
            return new CeasMecanic(id, brand, model, pret, stoc, curea, TipMecanism.valueOf(extraStr.toUpperCase()), extraInt);
        } else if (tip.equalsIgnoreCase("Smartwatch")) {
            return new Smartwatch(id, brand, model, pret, stoc, curea, extraStr, extraInt);
        }
        return null;
    }
}
