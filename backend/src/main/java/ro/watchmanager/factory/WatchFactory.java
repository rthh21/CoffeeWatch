package ro.watchmanager.factory;

import ro.watchmanager.model.*;

public class WatchFactory {
    public static Watch createWatch(String type, String id, Brand brand, String model, double price, int stock, Strap strap, String imageUrl, String extraStr, int extraInt) {
        String finalImageUrl = imageUrl != null ? imageUrl : "watches/luxury.webp";
        if (type.equalsIgnoreCase("Mechanical")) {
            return new MechanicalWatch(id, brand, model, price, stock, strap, finalImageUrl, MechanismType.valueOf(extraStr.toUpperCase()), extraInt);
        } else if (type.equalsIgnoreCase("Smartwatch")) {
            return new Smartwatch(id, brand, model, price, stock, strap, finalImageUrl, extraStr, extraInt);
        }
        return null;
    }
}
