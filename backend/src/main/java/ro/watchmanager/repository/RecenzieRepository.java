package ro.watchmanager.repository;

import ro.watchmanager.model.Review;
import ro.watchmanager.model.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RecenzieRepository extends GenericRepository<Review, Integer> {
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
    public void create(Review r) throws SQLException {
        String sql = "INSERT INTO Recenzie (utilizator, comentariu, nota) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getUser());
            stmt.setString(2, r.getText());
            stmt.setInt(3, r.getRating().getValue());
            stmt.executeUpdate();
        }
    }

    public void createWithWatch(Review r, String watchId) throws SQLException {
        String sql = "INSERT INTO Recenzie (ceas_id, utilizator, comentariu, nota) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, watchId);
            stmt.setString(2, r.getUser());
            stmt.setString(3, r.getText());
            stmt.setInt(4, r.getRating().getValue());
            stmt.executeUpdate();
        }
    }

    @Override
    public Review read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Recenzie WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToReview(rs);
            }
        }
        return null;
    }

    public List<Review> findByWatchId(String watchId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Recenzie WHERE ceas_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, watchId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
        }
        return reviews;
    }

    @Override
    public void update(Integer id, Review r) throws SQLException {
        String sql = "UPDATE Recenzie SET utilizator = ?, comentariu = ?, nota = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getUser());
            stmt.setString(2, r.getText());
            stmt.setInt(3, r.getRating().getValue());
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
    public List<Review> findAll() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Recenzie";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
        }
        return reviews;
    }

    private Review mapResultSetToReview(ResultSet rs) throws SQLException {
        int nota = rs.getInt("nota");
        Rating rating = switch (nota) {
            case 1 -> Rating.ONE;
            case 2 -> Rating.TWO;
            case 3 -> Rating.THREE;
            case 4 -> Rating.FOUR;
            case 5 -> Rating.FIVE;
            default -> Rating.THREE;
        };
        return new Review(
                rating,
                rs.getString("comentariu"),
                rs.getString("utilizator")
        );
    }
}
