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
        String sql = "INSERT INTO Ceas (id, brand_id, nume_model, pret, stoc, tip, mecanism, rezerva_putere, sistem_operare, autonomie_baterie) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceas.getId());
            stmt.setInt(2, 1); // Simplified for now
            stmt.setString(3, ceas.getNumeModel());
            stmt.setDouble(4, ceas.getPret());
            stmt.setInt(5, ceas.getStoc());
            if (ceas instanceof CeasMecanic) {
                CeasMecanic cm = (CeasMecanic) ceas;
                stmt.setString(6, "Mecanic");
                stmt.setString(7, cm.getTipMecanism().name());
                stmt.setInt(8, cm.getRezervaPutereOre());
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.INTEGER);
            } else {
                Smartwatch sw = (Smartwatch) ceas;
                stmt.setString(6, "Smartwatch");
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.INTEGER);
                stmt.setString(9, sw.getSistemOperare());
                stmt.setInt(10, sw.getCapacitateBaterieMah());
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
        String sql = "UPDATE Ceas SET nume_model = ?, pret = ?, stoc = ?, mecanism = ?, rezerva_putere = ?, sistem_operare = ?, autonomie_baterie = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceas.getNumeModel());
            stmt.setDouble(2, ceas.getPret());
            stmt.setInt(3, ceas.getStoc());
            if (ceas instanceof CeasMecanic) {
                CeasMecanic cm = (CeasMecanic) ceas;
                stmt.setString(4, cm.getTipMecanism().name());
                stmt.setInt(5, cm.getRezervaPutereOre());
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.INTEGER);
            } else {
                Smartwatch sw = (Smartwatch) ceas;
                stmt.setNull(4, Types.VARCHAR);
                stmt.setNull(5, Types.INTEGER);
                stmt.setString(6, sw.getSistemOperare());
                stmt.setInt(7, sw.getCapacitateBaterieMah());
            }
            stmt.setString(8, id);
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
            int rezerva = rs.getInt("rezerva_putere");
            return new CeasMecanic(id, brand, numeModel, pret, stoc, null, mecanism, rezerva);
        } else {
            String os = rs.getString("sistem_operare");
            int baterie = rs.getInt("autonomie_baterie");
            return new Smartwatch(id, brand, numeModel, pret, stoc, null, os, baterie);
        }
    }
}
