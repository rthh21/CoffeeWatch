package ro.watchmanager.repository;

import ro.watchmanager.model.Review;
import ro.watchmanager.model.Rating;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository extends GenericRepository<Review, Integer> {
    private static ReviewRepository instance;

    private ReviewRepository() {
        super();
    }

    public static ReviewRepository getInstance() {
        if (instance == null) {
            instance = new ReviewRepository();
        }
        return instance;
    }

    @Override
    public void create(Review r) throws SQLException {
        String sql = "INSERT INTO Review (user_name, text_content, rating) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, r.getUser());
            stmt.setString(2, r.getText());
            stmt.setInt(3, r.getRating().getValue());
            stmt.executeUpdate();
        }
    }

    public void createWithWatch(Review r, String watchId) throws SQLException {
        String sql = "INSERT INTO Review (watch_id, user_name, text_content, rating) VALUES (?, ?, ?, ?)";
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
        String sql = "SELECT * FROM Review WHERE id = ?";
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
        String sql = "SELECT * FROM Review WHERE watch_id = ?";
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
        String sql = "UPDATE Review SET user_name = ?, text_content = ?, rating = ? WHERE id = ?";
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
        String sql = "DELETE FROM Review WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Review> findAll() throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM Review";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                reviews.add(mapResultSetToReview(rs));
            }
        }
        return reviews;
    }

    private Review mapResultSetToReview(ResultSet rs) throws SQLException {
        int nota = rs.getInt("rating");
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
                rs.getString("text_content"),
                rs.getString("user_name")
        );
    }
}
