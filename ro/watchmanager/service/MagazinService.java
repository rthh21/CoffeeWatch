package ro.watchmanager.service;

import ro.watchmanager.model.*;
import java.time.LocalDate;
import java.util.*;

public class MagazinService {
    private TreeSet<Ceas> stocCeasuri;
    private HashMap<String, Client> clienti;
    private List<Comanda> istoricComenzi;

    public MagazinService() {
        this.stocCeasuri = new TreeSet<>();
        this.clienti = new HashMap<>();
        this.istoricComenzi = new ArrayList<>();
    }

    // 1. Adăugarea unui ceas nou în stoc
    public void adaugaCeas(Ceas ceas) {
        stocCeasuri.add(ceas);
    }

    // 2. Înregistrarea unui client nou
    public void inregistrareClient(Client client) {
        clienti.put(client.getEmail(), client);
    }

    // 3. Plasarea unei comenzi
    public void plasareComanda(Comanda comanda) {
        istoricComenzi.add(comanda);
        // Ajustam stocul
        for(Ceas c : comanda.getCeasuri()) {
            Optional<Ceas> gasit = stocCeasuri.stream().filter(x -> x.getId().equals(c.getId())).findFirst();
            if(gasit.isPresent()) {
                Ceas ceasInStoc = gasit.get();
                if(ceasInStoc.getStoc() > 0) {
                    ceasInStoc.setStoc(ceasInStoc.getStoc() - 1);
                }
            }
        }
    }

    // 4. Afișarea tuturor ceasurilor, sortate după preț
    public void afisareCeasuriSortate() {
        System.out.println("--- Ceasuri in stoc (Sortate dupa pret) ---");
        for(Ceas c : stocCeasuri) {
            System.out.println(c);
        }
    }

    // 5. Filtrarea ceasurilor după un anumit Brand
    public void filtrareDupaBrand(String numeBrand) {
        System.out.println("--- Ceasuri de la brandul " + numeBrand + " ---");
        for(Ceas c : stocCeasuri) {
            if(c.getBrand().getNume().equalsIgnoreCase(numeBrand)) {
                System.out.println(c);
            }
        }
    }

    // 6. Actualizarea stocului / prețului pentru un anumit ceas
    public void actualizareCeas(String idCeas, int stocNou, double pretNou) {
        Ceas ceasGasit = null;
        for(Ceas c : stocCeasuri) {
            if(c.getId().equals(idCeas)) {
                ceasGasit = c;
                break;
            }
        }
        if(ceasGasit != null) {
            // Eliminam si adaugam la loc pentru a reface sortarea daca pretul e diferit
            stocCeasuri.remove(ceasGasit);
            ceasGasit.setStoc(stocNou);
            ceasGasit.setPret(pretNou);
            stocCeasuri.add(ceasGasit);
        }
    }

    // 7. Ștergerea unui ceas din sistem
    public void stergereCeas(String idCeas) {
        stocCeasuri.removeIf(c -> c.getId().equals(idCeas));
    }

    // 8. Afișarea istoricului de comenzi pentru un client specific
    public void afisareIstoricComenziClient(String emailClient) {
        System.out.println("--- Istoric comenzi pt " + emailClient + " ---");
        for(Comanda comanda : istoricComenzi) {
            if(comanda.getClient().getEmail().equals(emailClient)) {
                System.out.println(comanda);
            }
        }
    }

    // 9. Adăugarea unei recenzii pentru un produs
    public void adaugaRecenzie(String idCeas, Recenzie rec) {
        for(Ceas c : stocCeasuri) {
            if(c.getId().equals(idCeas)) {
                c.adaugaRecenzie(rec);
                break;
            }
        }
    }

    // 10. Calcularea valorii totale a comenzilor plasate într-o zi
    public double calculeazaValoareTotalaComenziPeZi(LocalDate data) {
        double total = 0;
        for(Comanda c : istoricComenzi) {
            if(c.getDataComanda().equals(data)) {
                total += c.getValoareTotala();
            }
        }
        return total;
    }
}
