package ro.watchmanager.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {
    private static DatabaseConnectionManager instance;
    private Connection connection;
    private static final String URL = "jdbc:postgresql://localhost:5432/watch_manager";
    private static final String USER = "rth";
    private static final String PASSWORD = "password";

    private DatabaseConnectionManager() {
        try {
            // Load the explicit driver for PostgreSQL
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
