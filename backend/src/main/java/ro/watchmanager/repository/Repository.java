package ro.watchmanager.repository;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T, ID> {
    void create(T entity) throws SQLException;
    T read(ID id) throws SQLException;
    void update(ID id, T entity) throws SQLException;
    void delete(ID id) throws SQLException;
    List<T> findAll() throws SQLException;
}
