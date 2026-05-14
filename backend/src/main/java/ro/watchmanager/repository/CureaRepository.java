package ro.watchmanager.repository;

import ro.watchmanager.model.Curea;
import java.sql.*;

public class CureaRepository {
    private Connection connection;

    public CureaRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void create(Curea curea) throws SQLException {
        String sql = "INSERT INTO Curea (material, latime) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curea.getMaterial());
            stmt.setInt(2, curea.getDimensiuneInMm());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Curea WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
