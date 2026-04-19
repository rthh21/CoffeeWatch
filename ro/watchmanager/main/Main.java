package ro.watchmanager.main;

import ro.watchmanager.model.*;
import ro.watchmanager.service.MagazinService;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        MagazinService service = new MagazinService();

        // Testare Actiunea 1: Adaugare ceas + creare obiecte
        Brand b1 = new Brand("Rolex", "Elvetia");
        Brand b2 = new Brand("Apple", "SUA");
        Curea c1 = new Curea("Otel", 20);
        Curea c2 = new Curea("Silicon", 22);

        CeasMecanic rolex = new CeasMecanic("C1", b1, "Submariner", 45000.0, 5, c1, "Automat", 48);
        Smartwatch appleW = new Smartwatch("C2", b2, "Watch Series 9", 2500.0, 20, c2, "watchOS", 300);
        
        service.adaugaCeas(rolex);
        service.adaugaCeas(appleW);

        // Testare Actiunea 2: Inregistrare client
        Client client1 = new Client("ion@test.ro", "Ion Popescu", "0722222222");
        service.inregistrareClient(client1);

        // Testare Actiunea 4: Afisare sortata
        System.out.println("=== Dupa adaugare (Se va vedea Apple Watch primul, avand pret mai mic) ===");
        service.afisareCeasuriSortate();

        // Testare Actiunea 5: Filtrare dupa brand
        System.out.println("\n=== Filtrare Rolex ===");
        service.filtrareDupaBrand("Rolex");

        // Testare Actiunea 6: Actualizare ceas
        service.actualizareCeas("C2", 15, 2400.0);
        System.out.println("\n=== Dupa actualizare Apple Watch la pret 2400 ===");
        service.afisareCeasuriSortate();

        // Testare Actiunea 9: Adaugare recenzie
        service.adaugaRecenzie("C1", new Recenzie(5, "Exceptional!"));

        // Testare Actiunea 3: Plasare comanda
        Comanda comanda1 = new Comanda("CMD1", client1, LocalDate.now());
        comanda1.adaugaCeas(rolex);
        service.plasareComanda(comanda1);

        // Testare Actiunea 8: Istoric comenzi
        System.out.println("\n=== Istoric client ion@test.ro ===");
        service.afisareIstoricComenziClient("ion@test.ro");

        // Testare Actiunea 10: Valoare comenzi pe zi
        System.out.println("\n=== Valoare comenzi astazi ===");
        System.out.println(service.calculeazaValoareTotalaComenziPeZi(LocalDate.now()) + " RON");

        // Testare Actiunea 7: Stergere ceas
        service.stergereCeas("C2");
        System.out.println("\n=== Dupa stergere C2 ===");
        service.afisareCeasuriSortate();
    }
}
