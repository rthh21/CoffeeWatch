package ro.watchmanager.repository;

import ro.watchmanager.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CeasRepository extends GenericRepository<Ceas, String> {
    private static CeasRepository instance;

    private CeasRepository() {
        super();
    }

    public static CeasRepository getInstance() {
        if (instance == null) {
            instance = new CeasRepository();
        }
        return instance;
    }

    @Override
    public void create(Ceas ceas) throws SQLException {
        String sql = "INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, autonomie_baterie) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceas.getId());
            // We assume brand exists or we handle brand_id. For now, we search for brand by name or similar.
            // Simplified: we'll use 1 or try to find the real ID.
            stmt.setInt(2, 1); 
            stmt.setString(3, ceas.getNumeModel());
            stmt.setDouble(4, ceas.getPret());
            stmt.setInt(5, ceas.getStoc());
            if (ceas instanceof CeasMecanic) {
                stmt.setString(6, "Mecanic");
                stmt.setString(7, ((CeasMecanic) ceas).getTipMecanism().name());
                stmt.setNull(8, Types.INTEGER);
            } else {
                stmt.setString(6, "Smartwatch");
                stmt.setNull(7, Types.VARCHAR);
                stmt.setInt(8, ((Smartwatch) ceas).getCapacitateBaterieMah());
            }
            stmt.executeUpdate();
        }
    }

    @Override
    public Ceas read(String id) throws SQLException {
        String sql = "SELECT * FROM Ceas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCeas(rs);
            }
        }
        return null;
    }

    @Override
    public void update(String id, Ceas ceas) throws SQLException {
        String sql = "UPDATE Ceas SET nume_model = ?, pret = ?, stoc = ?, mecanism = ?, autonomie_baterie = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceas.getNumeModel());
            stmt.setDouble(2, ceas.getPret());
            stmt.setInt(3, ceas.getStoc());
            if (ceas instanceof CeasMecanic) {
                stmt.setString(4, ((CeasMecanic) ceas).getTipMecanism().name());
                stmt.setNull(5, Types.INTEGER);
            } else {
                stmt.setNull(4, Types.VARCHAR);
                stmt.setInt(5, ((Smartwatch) ceas).getCapacitateBaterieMah());
            }
            stmt.setString(6, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Ceas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Ceas> findAll() throws SQLException {
        List<Ceas> ceasuri = new ArrayList<>();
        String sql = "SELECT * FROM Ceas";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                ceasuri.add(mapResultSetToCeas(rs));
            }
        }
        return ceasuri;
    }

    private Ceas mapResultSetToCeas(ResultSet rs) throws SQLException {
        String tip = rs.getString("tip");
        String id = rs.getString("id");
        String numeModel = rs.getString("nume_model");
        double pret = rs.getDouble("pret");
        int stoc = rs.getInt("stoc");
        int brandId = rs.getInt("brand_id");
        
        Brand brand = BrandRepository.getInstance().read(brandId);
        if (brand == null) {
            brand = new Brand("Unknown", "Unknown");
        }

        if ("Mecanic".equals(tip)) {
            TipMecanism mecanism = TipMecanism.valueOf(rs.getString("mecanism"));
            return new CeasMecanic(id, brand, numeModel, pret, stoc, null, mecanism);
        } else {
            int baterie = rs.getInt("autonomie_baterie");
            return new Smartwatch(id, brand, numeModel, pret, stoc, null, baterie);
        }
    }
}
