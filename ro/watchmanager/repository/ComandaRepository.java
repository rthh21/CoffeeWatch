package ro.watchmanager.repository;

import ro.watchmanager.model.Comanda;
import java.sql.*;

public class ComandaRepository {
    private Connection connection;

    public ComandaRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void create(Comanda c) throws SQLException {
        String sql = "INSERT INTO Comanda (id_comanda, client_email, data_comanda, valoare_totala) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getIdComanda());
            stmt.setString(2, c.getClient().getEmail());
            stmt.setDate(3, Date.valueOf(c.getDataComanda()));
            stmt.setDouble(4, c.getValoareTotala());
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Comanda WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
}
