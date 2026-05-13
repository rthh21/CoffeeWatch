package ro.watchmanager.repository;

import ro.watchmanager.model.Client;
import java.sql.*;

public class ClientRepository {
    private Connection connection;

    public ClientRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }

    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO Client (email, nume, telefon) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getEmail());
            stmt.setString(2, client.getNume());
            stmt.setString(3, client.getTelefon());
            stmt.executeUpdate();
        }
    }

    public Client read(String email) throws SQLException {
        String sql = "SELECT * FROM Client WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Client(rs.getString("email"), rs.getString("nume"), rs.getString("telefon"));
            }
        }
        return null;
    }

    public void update(Client client) throws SQLException {
        String sql = "UPDATE Client SET nume = ?, telefon = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getNume());
            stmt.setString(2, client.getTelefon());
            stmt.setString(3, client.getEmail());
            stmt.executeUpdate();
        }
    }

    public void delete(String email) throws SQLException {
        String sql = "DELETE FROM Client WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }
}
