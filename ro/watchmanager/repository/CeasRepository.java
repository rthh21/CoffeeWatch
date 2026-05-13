package ro.watchmanager.repository;

import ro.watchmanager.model.*;
import java.sql.*;

public class CeasRepository {
    private Connection connection;

    public CeasRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void create(Ceas ceas) throws SQLException {
        String sql = "INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, autonomie_baterie) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceas.getId());
            stmt.setInt(2, 1); // Mock brand_id
            stmt.setString(3, ceas.getNumeModel());
            stmt.setDouble(4, ceas.getPret());
            stmt.setInt(5, ceas.getStoc());
            if (ceas instanceof CeasMecanic) {
                stmt.setString(6, "Mecanic");
                stmt.setString(7, ((CeasMecanic) ceas).getMecanism());
                stmt.setNull(8, Types.INTEGER);
            } else {
                stmt.setString(6, "Smartwatch");
                stmt.setNull(7, Types.VARCHAR);
                stmt.setInt(8, ((Smartwatch) ceas).getAutonomieBaterie());
            }
            stmt.executeUpdate();
        }
    }

    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Ceas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }
    
    // Alte metode CRUD...
}
