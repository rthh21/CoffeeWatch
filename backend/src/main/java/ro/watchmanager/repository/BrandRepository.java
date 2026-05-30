package ro.watchmanager.repository;

import ro.watchmanager.model.Brand;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository extends GenericRepository<Brand, Integer> {
    private static BrandRepository instance;

    private BrandRepository() {
        super();
    }

    public static BrandRepository getInstance() {
        if (instance == null) {
            instance = new BrandRepository();
        }
        return instance;
    }

    @Override
    public void create(Brand brand) throws SQLException {
        String sql = "INSERT INTO Brand (name, country_of_origin) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, brand.getName());
            stmt.setString(2, brand.getCountryOfOrigin());
            stmt.executeUpdate();
        }
    }

    @Override
    public Brand read(Integer id) throws SQLException {
        String sql = "SELECT * FROM Brand WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Brand(rs.getString("name"), rs.getString("country_of_origin"));
            }
        }
        return null;
    }

    @Override
    public void update(Integer id, Brand brand) throws SQLException {
        String sql = "UPDATE Brand SET name = ?, country_of_origin = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, brand.getName());
            stmt.setString(2, brand.getCountryOfOrigin());
            stmt.setInt(3, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws SQLException {
        String sql = "DELETE FROM Brand WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Brand> findAll() throws SQLException {
        List<Brand> brands = new ArrayList<>();
        String sql = "SELECT * FROM Brand";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                brands.add(new Brand(rs.getString("name"), rs.getString("country_of_origin")));
            }
        }
        return brands;
    }
}
