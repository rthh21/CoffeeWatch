package ro.watchmanager.repository;

import ro.watchmanager.model.Curea;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CureaRepository extends GenericRepository<Curea, Integer> {
    private static CureaRepository instance;

    private CureaRepository() {
        super();
    }

    public static CureaRepository getInstance() {
        if (instance == null) {
            instance = new CureaRepository();
        }
        return instance;
    }

    @Override
    public void create(Curea curea) throws SQLException {
        String sql = "INSERT INTO Curea (material, latime) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curea.getMaterial());
            stmt.setInt(2, curea.getSizeInMm());
            stmt.executeUpdate();
        }
    }

    @Override
    public Curea read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Curea WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Curea(rs.getString("material"), rs.getInt("latime"));
            }
        }
        return null;
    }

    @Override
    public void update(Integer id, Curea curea) throws SQLException {
        String sql = "UPDATE Curea SET material = ?, latime = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, curea.getMaterial());
            stmt.setInt(2, curea.getSizeInMm());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Curea WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Curea> findAll() throws SQLException {
        List<Curea> straps = new ArrayList<>();
        String sql = "SELECT * FROM Curea";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                straps.add(new Curea(rs.getString("material"), rs.getInt("latime")));
            }
        }
        return straps;
    }
}
