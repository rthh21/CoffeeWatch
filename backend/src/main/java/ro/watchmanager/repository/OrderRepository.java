package ro.watchmanager.repository;

import ro.watchmanager.model.Order;
import ro.watchmanager.model.Client;
import ro.watchmanager.model.Watch;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository extends GenericRepository<Order, String> {
    private static OrderRepository instance;

    private OrderRepository() {
        super();
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    @Override
    public void create(Order c) throws SQLException {
        String sql = "INSERT INTO Orders (order_id, client_email, order_date, total_value) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getOrderId());
            stmt.setString(2, c.getClient().getEmail());
            stmt.setDate(3, Date.valueOf(c.getOrderDate()));
            stmt.setDouble(4, c.getTotalValue());
            stmt.executeUpdate();
        }

        
        String junctionSql = "INSERT INTO Order_Watch (order_id, watch_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(junctionSql)) {
            for (Watch ceas : c.getWatches()) {
                stmt.setString(1, c.getOrderId());
                stmt.setString(2, ceas.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    @Override
    public Order read(String id) throws SQLException {
        String sql = "SELECT * FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToOrder(rs);
            }
        }
        return null;
    }

    @Override
    public void update(String id, Order c) throws SQLException {
        String sql = "UPDATE Orders SET client_email = ?, order_date = ?, total_value = ? WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, c.getClient().getEmail());
            stmt.setDate(2, Date.valueOf(c.getOrderDate()));
            stmt.setDouble(3, c.getTotalValue());
            stmt.setString(4, id);
            stmt.executeUpdate();
        }
        
        
        String deleteJunction = "DELETE FROM Order_Watch WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteJunction)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
        
        create(c);
    }

    @Override
    public void delete(String id) throws SQLException {
        
        String deleteJunction = "DELETE FROM Order_Watch WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteJunction)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }

        String sql = "DELETE FROM Orders WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Order> findAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
        }
        return orders;
    }

    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        String id = rs.getString("order_id");
        String email = rs.getString("client_email");
        Date data = rs.getDate("order_date");
        
        Client client = ClientRepository.getInstance().read(email);
        Order order = new Order(id, client, data.toLocalDate());
        
        
        String junctionSql = "SELECT watch_id FROM Order_Watch WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(junctionSql)) {
            stmt.setString(1, id);
            ResultSet rsJ = stmt.executeQuery();
            while (rsJ.next()) {
                Watch c = WatchRepository.getInstance().read(rsJ.getString("watch_id"));
                if (c != null) {
                    order.addWatch(c);
                }
            }
        }
        
        return order;
    }
}
