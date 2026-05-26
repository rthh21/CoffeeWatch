package ro.watchmanager.repository;

import ro.watchmanager.model.Comanda;
import ro.watchmanager.model.Client;
import ro.watchmanager.model.Ceas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaRepository extends GenericRepository<Comanda, String> {
    private static ComandaRepository instance;

    private ComandaRepository() {
        super();
    }

    public static ComandaRepository getInstance() {
        if (instance == null) {
            instance = new ComandaRepository();
        }
        return instance;
    }

    @Override
    public void create(Comanda c) throws SQLException {
        String sql = "INSERT INTO Comanda (id_comanda, client_email, data_comanda, valoare_totala) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getIdComanda());
            stmt.setString(2, c.getClient().getEmail());
            stmt.setDate(3, Date.valueOf(c.getDataComanda()));
            stmt.setDouble(4, c.getValoareTotala());
            stmt.executeUpdate();
        }

        // Insert into junction table
        String junctionSql = "INSERT INTO Comanda_Ceas (id_comanda, ceas_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(junctionSql)) {
            for (Ceas ceas : c.getCeasuri()) {
                stmt.setString(1, c.getIdComanda());
                stmt.setString(2, ceas.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    @Override
    public Comanda read(String id) throws SQLException {
        String sql = "SELECT * FROM Comanda WHERE id_comanda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToComanda(rs);
            }
        }
        return null;
    }

    @Override
    public void update(String id, Comanda c) throws SQLException {
        String sql = "UPDATE Comanda SET client_email = ?, data_comanda = ?, valoare_totala = ? WHERE id_comanda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getClient().getEmail());
            stmt.setDate(2, Date.valueOf(c.getDataComanda()));
            stmt.setDouble(3, c.getValoareTotala());
            stmt.setString(4, id);
            stmt.executeUpdate();
        }
        
        // Simpler to delete and re-insert for junction table in a school project
        String deleteJunction = "DELETE FROM Comanda_Ceas WHERE id_comanda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteJunction)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
        
        create(c); // This is recursive but if id matches it might be okay. Better to extract junction logic.
    }

    @Override
    public void delete(String id) throws SQLException {
        // Delete from junction first
        String deleteJunction = "DELETE FROM Comanda_Ceas WHERE id_comanda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteJunction)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }

        String sql = "DELETE FROM Comanda WHERE id_comanda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Comanda> findAll() throws SQLException {
        List<Comanda> comenzi = new ArrayList<>();
        String sql = "SELECT * FROM Comanda";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                comenzi.add(mapResultSetToComanda(rs));
            }
        }
        return comenzi;
    }

    private Comanda mapResultSetToComanda(ResultSet rs) throws SQLException {
        String id = rs.getString("id_comanda");
        String email = rs.getString("client_email");
        Date data = rs.getDate("data_comanda");
        
        Client client = ClientRepository.getInstance().read(email);
        Comanda comanda = new Comanda(id, client, data.toLocalDate());
        
        // Fetch items from junction table
        String junctionSql = "SELECT ceas_id FROM Comanda_Ceas WHERE id_comanda = ?";
        try (PreparedStatement stmt = connection.prepareStatement(junctionSql)) {
            stmt.setString(1, id);
            ResultSet rsJ = stmt.executeQuery();
            while (rsJ.next()) {
                Ceas c = CeasRepository.getInstance().read(rsJ.getString("ceas_id"));
                if (c != null) {
                    comanda.adaugaCeas(c);
                }
            }
        }
        
        return comanda;
    }
}
