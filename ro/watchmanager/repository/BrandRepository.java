package ro.watchmanager.repository;

import ro.watchmanager.model.Brand;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository {
    private Connection connection;

    public BrandRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void create(Brand brand) throws SQLException {
        String sql = "INSERT INTO Brand (nume) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, brand.getNume());
            stmt.executeUpdate();
        }
    }

    public Brand read(int id) throws SQLException {
        String sql = "SELECT * FROM Brand WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Brand(rs.getString("nume"));
            }
        }
        return null;
    }

    public void update(int id, Brand brand) throws SQLException {
        String sql = "UPDATE Brand SET nume = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, brand.getNume());
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Brand WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
