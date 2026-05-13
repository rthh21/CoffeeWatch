package ro.watchmanager.factory;

import ro.watchmanager.model.*;

public class CeasFactory {
    public static Ceas creeazaCeas(String tip, String id, Brand brand, String model, double pret, int stoc, Curea curea, Object extra) {
        if (tip.equalsIgnoreCase("Mecanic")) {
            return new CeasMecanic(id, brand, model, pret, stoc, curea, (String) extra);
        } else if (tip.equalsIgnoreCase("Smartwatch")) {
            return new Smartwatch(id, brand, model, pret, stoc, curea, (Integer) extra);
        }
        return null;
    }
}
