package ro.watchmanager.service;

import ro.watchmanager.exception.StocInsuficientException;
import ro.watchmanager.model.*;
import java.time.LocalDate;
import java.util.*;

public class MagazinService {
    private TreeSet<Ceas> stocCeasuri;
    private HashMap<String, Client> clienti;
    private List<Comanda> istoricComenzi;
    private AuditService auditService;

    public MagazinService() {
        this.stocCeasuri = new TreeSet<>();
        this.clienti = new HashMap<>();
        this.istoricComenzi = new ArrayList<>();
        this.auditService = AuditService.getInstance();
    }

    public TreeSet<Ceas> getStocCeasuri() {
        return stocCeasuri;
    }

    public List<Ceas> getCeasuriByBrand(String numeBrand) {
        return stocCeasuri.stream()
                .filter(c -> c.getBrand().getNume().equalsIgnoreCase(numeBrand))
                .toList();
    }

    public void adaugaCeas(Ceas ceas) {
        stocCeasuri.add(ceas);
        auditService.logEveniment("adaugaCeas");
    }

    public void inregistrareClient(Client client) {
        clienti.put(client.getEmail(), client);
        auditService.logEveniment("inregistrareClient");
    }

    public void plasareComanda(Comanda comanda) throws StocInsuficientException {
        auditService.logEveniment("plasareComanda");
        for (Ceas c : comanda.getCeasuri()) {
            Optional<Ceas> gasit = stocCeasuri.stream().filter(x -> x.getId().equals(c.getId())).findFirst();
            if (gasit.isPresent()) {
                Ceas ceasInStoc = gasit.get();
                if (ceasInStoc.getStoc() <= 0) {
                    throw new StocInsuficientException("Stoc insuficient pentru: " + ceasInStoc.getNumeModel());
                }
                ceasInStoc.setStoc(ceasInStoc.getStoc() - 1);
            }
        }
        istoricComenzi.add(comanda);
    }

    public void afisareCeasuriSortate() {
        auditService.logEveniment("afisareCeasuriSortate");
        stocCeasuri.forEach(System.out::println);
    }

    public void filtrareDupaBrand(String numeBrand) {
        auditService.logEveniment("filtrareDupaBrand");
        stocCeasuri.stream()
                .filter(c -> c.getBrand().getNume().equalsIgnoreCase(numeBrand))
                .forEach(System.out::println);
    }

    public void actualizareCeas(String idCeas, int stocNou, double pretNou) {
        auditService.logEveniment("actualizareCeas");
        stocCeasuri.stream().filter(c -> c.getId().equals(idCeas)).findFirst().ifPresent(c -> {
            stocCeasuri.remove(c);
            c.setStoc(stocNou);
            c.setPret(pretNou);
            stocCeasuri.add(c);
        });
    }

    public void stergereCeas(String idCeas) {
        auditService.logEveniment("stergereCeas");
        stocCeasuri.removeIf(c -> c.getId().equals(idCeas));
    }

    public void afisareIstoricComenziClient(String emailClient) {
        auditService.logEveniment("afisareIstoricComenziClient");
        istoricComenzi.stream()
                .filter(c -> c.getClient().getEmail().equals(emailClient))
                .forEach(System.out::println);
    }

    public void adaugaRecenzie(String idCeas, Recenzie rec) {
        auditService.logEveniment("adaugaRecenzie");
        stocCeasuri.stream().filter(c -> c.getId().equals(idCeas)).findFirst().ifPresent(c -> c.adaugaRecenzie(rec));
    }

    public double calculeazaValoareTotalaComenziPeZi(LocalDate data) {
        auditService.logEveniment("calculeazaValoareTotalaComenziPeZi");
        return istoricComenzi.stream()
                .filter(c -> c.getDataComanda().equals(data))
                .mapToDouble(Comanda::getValoareTotala)
                .sum();
    }

    // 5 NOI METODE
    public void aplicaDiscountGeneral(double procent) {
        auditService.logEveniment("aplicaDiscountGeneral");
        stocCeasuri.forEach(c -> c.aplicaDiscount(procent));
    }

    public List<Ceas> getCeasuriInStocLimitat(int prag) {
        auditService.logEveniment("getCeasuriInStocLimitat");
        return stocCeasuri.stream()
                .filter(c -> c.getStoc() < prag)
                .toList();
    }

    public void stergeClient(String email) {
        auditService.logEveniment("stergeClient");
        clienti.remove(email);
    }

    public double getValoareMedieComenzi() {
        auditService.logEveniment("getValoareMedieComenzi");
        return istoricComenzi.stream()
                .mapToDouble(Comanda::getValoareTotala)
                .average()
                .orElse(0.0);
    }

    public List<Ceas> getTopProduseRecenzate() {
        auditService.logEveniment("getTopProduseRecenzate");
        return stocCeasuri.stream()
                .sorted((c1, c2) -> Integer.compare(c2.getRecenzii().size(), c1.getRecenzii().size()))
                .toList();
    }
}
