package ro.watchmanager.repository;

import ro.watchmanager.model.Strap;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StrapRepository extends GenericRepository<Strap, Integer> {
    private static StrapRepository instance;

    private StrapRepository() {
        super();
    }

    public static StrapRepository getInstance() {
        if (instance == null) {
            instance = new StrapRepository();
        }
        return instance;
    }

    @Override
    public void create(Strap strap) throws SQLException {
        String sql = "INSERT INTO Strap (material, size_mm) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, strap.getMaterial());
            stmt.setInt(2, strap.getSizeInMm());
            stmt.executeUpdate();
        }
    }

    @Override
    public Strap read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Strap WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Strap(rs.getString("material"), rs.getInt("size_mm"));
            }
        }
        return null;
    }

    @Override
    public void update(Integer id, Strap strap) throws SQLException {
        String sql = "UPDATE Strap SET material = ?, size_mm = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, strap.getMaterial());
            stmt.setInt(2, strap.getSizeInMm());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Strap WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Strap> findAll() throws SQLException {
        List<Strap> straps = new ArrayList<>();
        String sql = "SELECT * FROM Strap";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                straps.add(new Strap(rs.getString("material"), rs.getInt("size_mm")));
            }
        }
        return straps;
    }
}
