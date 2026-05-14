package ro.watchmanager.repository;

import java.sql.Connection;

public abstract class GenericRepository<T, ID> implements Repository<T, ID> {
    protected Connection connection;

    protected GenericRepository() {
        this.connection = DatabaseConnectionManager.getInstance().getConnection();
    }
}
