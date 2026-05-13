package ro.watchmanager.repository;

import ro.watchmanager.model.Recenzie;
import java.sql.*;

public class RecenzieRepository {
    private Connection connection;

    public RecenzieRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void create(Recenzie r, String ceasId) throws SQLException {
        String sql = "INSERT INTO Recenzie (ceas_id, utilizator, comentariu, nota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceasId);
            stmt.setString(2, r.getUtilizator());
            stmt.setString(3, r.getComentariu());
            stmt.setInt(4, r.getNota());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Recenzie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
