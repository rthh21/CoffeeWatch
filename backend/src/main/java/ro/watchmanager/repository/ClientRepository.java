package ro.watchmanager.repository;

import ro.watchmanager.model.Client;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository extends GenericRepository<Client, String> {
    private static ClientRepository instance;

    private ClientRepository() {
        super();
    }

    public static ClientRepository getInstance() {
        if (instance == null) {
            instance = new ClientRepository();
        }
        return instance;
    }

    @Override
    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO Client (email, nume, telefon) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getEmail());
            stmt.setString(2, client.getName());
            stmt.setString(3, client.getPhoneNumber());
            stmt.executeUpdate();
        }
    }

    @Override
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

    @Override
    public void update(String email, Client client) throws SQLException {
        String sql = "UPDATE Client SET nume = ?, telefon = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getPhoneNumber());
            stmt.setString(3, email);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String email) throws SQLException {
        String sql = "DELETE FROM Client WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                clients.add(new Client(rs.getString("email"), rs.getString("nume"), rs.getString("telefon")));
            }
        }
        return clients;
    }
}
