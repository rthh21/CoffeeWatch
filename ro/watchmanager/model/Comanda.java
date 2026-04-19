package ro.watchmanager.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Comanda {
    private String idComanda;
    private Client client;
    private List<Ceas> ceasuri;
    private LocalDate dataComanda;
    private double valoareTotala;

    public Comanda(String idComanda, Client client, LocalDate dataComanda) {
        this.idComanda = idComanda;
        this.client = client;
        this.dataComanda = dataComanda;
        this.ceasuri = new ArrayList<>();
        this.valoareTotala = 0;
    }

    public void adaugaCeas(Ceas ceas) {
        this.ceasuri.add(ceas);
        this.valoareTotala += ceas.getPret();
    }

    public String getIdComanda() { return idComanda; }
    public void setIdComanda(String idComanda) { this.idComanda = idComanda; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Ceas> getCeasuri() { return ceasuri; }

    public LocalDate getDataComanda() { return dataComanda; }
    public void setDataComanda(LocalDate dataComanda) { this.dataComanda = dataComanda; }

    public double getValoareTotala() { return valoareTotala; }

    @Override
    public String toString() {
        return "Comanda " + idComanda + " (Data: " + dataComanda + ") | Client: " + client.getNume() + " | Valoare: " + valoareTotala + " RON | Produse: " + ceasuri.size();
    }
}
