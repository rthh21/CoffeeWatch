package ro.watchmanager.repository;

import ro.watchmanager.model.Recenzie;
import ro.watchmanager.model.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecenzieRepository extends GenericRepository<Recenzie, Integer> {
    private static RecenzieRepository instance;

    private RecenzieRepository() {
        super();
    }

    public static RecenzieRepository getInstance() {
        if (instance == null) {
            instance = new RecenzieRepository();
        }
        return instance;
    }

    @Override
    public void create(Recenzie r) throws SQLException {
        String sql = "INSERT INTO Recenzie (utilizator, comentariu, nota) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getUtilizator());
            stmt.setString(2, r.getText());
            stmt.setInt(3, r.getRating().getValoare());
            stmt.executeUpdate();
        }
    }

    public void createWithCeas(Recenzie r, String ceasId) throws SQLException {
        String sql = "INSERT INTO Recenzie (ceas_id, utilizator, comentariu, nota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceasId);
            stmt.setString(2, r.getUtilizator());
            stmt.setString(3, r.getText());
            stmt.setInt(4, r.getRating().getValoare());
            stmt.executeUpdate();
        }
    }

    @Override
    public Recenzie read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Recenzie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToRecenzie(rs);
            }
        }
        return null;
    }

    public List<Recenzie> findByCeasId(String ceasId) throws SQLException {
        List<Recenzie> recenzii = new ArrayList<>();
        String sql = "SELECT * FROM Recenzie WHERE ceas_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ceasId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                recenzii.add(mapResultSetToRecenzie(rs));
            }
        }
        return recenzii;
    }

    @Override
    public void update(Integer id, Recenzie r) throws SQLException {
        String sql = "UPDATE Recenzie SET utilizator = ?, comentariu = ?, nota = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getUtilizator());
            stmt.setString(2, r.getText());
            stmt.setInt(3, r.getRating().getValoare());
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Recenzie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Recenzie> findAll() throws SQLException {
        List<Recenzie> recenzii = new ArrayList<>();
        String sql = "SELECT * FROM Recenzie";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                recenzii.add(mapResultSetToRecenzie(rs));
            }
        }
        return recenzii;
    }

    private Recenzie mapResultSetToRecenzie(ResultSet rs) throws SQLException {
        int nota = rs.getInt("nota");
        Rating rating = switch (nota) {
            case 1 -> Rating.UNU;
            case 2 -> Rating.DOI;
            case 3 -> Rating.TREI;
            case 4 -> Rating.PATRU;
            case 5 -> Rating.CINCI;
            default -> Rating.TREI;
        };
        return new Recenzie(
                rs.getString("utilizator"),
                rs.getString("comentariu"),
                rating
        );
    }
}
