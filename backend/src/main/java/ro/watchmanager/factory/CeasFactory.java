package ro.watchmanager.factory;

import ro.watchmanager.model.*;

public class CeasFactory {
    public static Ceas createWatch(String type, String id, Brand brand, String model, double price, int stock, Curea strap, String extraStr, int extraInt) {
        if (type.equalsIgnoreCase("Mechanical")) {
            return new CeasMecanic(id, brand, model, price, stock, strap, TipMecanism.valueOf(extraStr.toUpperCase()), extraInt);
        } else if (type.equalsIgnoreCase("Smartwatch")) {
            return new Smartwatch(id, brand, model, price, stock, strap, extraStr, extraInt);
        }
        return null;
    }
}
