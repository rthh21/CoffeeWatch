package ro.watchmanager.repository;

import ro.watchmanager.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WatchRepository extends GenericRepository<Watch, String> {
    private static WatchRepository instance;

    private WatchRepository() {
        super();
    }

    public static WatchRepository getInstance() {
        if (instance == null) {
            instance = new WatchRepository();
        }
        return instance;
    }

    @Override
    public void create(Watch watch) throws SQLException {
        String sql = "INSERT INTO Watch (id, brand_id, model_name, price, stock, type, mechanism_type, power_reserve, operating_system, battery_capacity, image_url) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, watch.getId());
            stmt.setInt(2, 1); 
            stmt.setString(3, watch.getModelName());
            stmt.setDouble(4, watch.getPrice());
            stmt.setInt(5, watch.getStock());
            if (watch instanceof MechanicalWatch) {
                MechanicalWatch cm = (MechanicalWatch) watch;
                stmt.setString(6, "Mechanical");
                stmt.setString(7, cm.getMechanismType().name());
                stmt.setInt(8, cm.getPowerReserveHours());
                stmt.setNull(9, Types.VARCHAR);
                stmt.setNull(10, Types.INTEGER);
            } else {
                Smartwatch sw = (Smartwatch) watch;
                stmt.setString(6, "Smartwatch");
                stmt.setNull(7, Types.VARCHAR);
                stmt.setNull(8, Types.INTEGER);
                stmt.setString(9, sw.getOperatingSystem());
                stmt.setInt(10, sw.getBatteryCapacityMah());
            }
            stmt.setString(11, watch.getImageUrl());
            stmt.executeUpdate();
        }
    }

    @Override
    public Watch read(String id) throws SQLException {
        String sql = "SELECT * FROM Watch WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToWatch(rs);
            }
        }
        return null;
    }

    @Override
    public void update(String id, Watch watch) throws SQLException {
        String sql = "UPDATE Watch SET model_name = ?, price = ?, stock = ?, mechanism_type = ?, power_reserve = ?, operating_system = ?, battery_capacity = ?, image_url = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, watch.getModelName());
            stmt.setDouble(2, watch.getPrice());
            stmt.setInt(3, watch.getStock());
            if (watch instanceof MechanicalWatch) {
                MechanicalWatch cm = (MechanicalWatch) watch;
                stmt.setString(4, cm.getMechanismType().name());
                stmt.setInt(5, cm.getPowerReserveHours());
                stmt.setNull(6, Types.VARCHAR);
                stmt.setNull(7, Types.INTEGER);
            } else {
                Smartwatch sw = (Smartwatch) watch;
                stmt.setNull(4, Types.VARCHAR);
                stmt.setNull(5, Types.INTEGER);
                stmt.setString(6, sw.getOperatingSystem());
                stmt.setInt(7, sw.getBatteryCapacityMah());
            }
            stmt.setString(8, watch.getImageUrl());
            stmt.setString(9, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(String id) throws SQLException {
        String sql = "DELETE FROM Watch WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Watch> findAll() throws SQLException {
        List<Watch> watches = new ArrayList<>();
        String sql = "SELECT * FROM Watch";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                watches.add(mapResultSetToWatch(rs));
            }
        }
        return watches;
    }

    private Watch mapResultSetToWatch(ResultSet rs) throws SQLException {
        String type = rs.getString("type");
        String id = rs.getString("id");
        String modelName = rs.getString("model_name");
        double price = rs.getDouble("price");
        int stock = rs.getInt("stock");
        int brandId = rs.getInt("brand_id");
        String imageUrl = rs.getString("image_url");
        
        Brand brand = BrandRepository.getInstance().read(brandId);
        if (brand == null) {
            brand = new Brand("Unknown", "Unknown");
        }

        if ("Mechanical".equals(type)) {
            MechanismType mechanism = MechanismType.valueOf(rs.getString("mechanism_type"));
            int reserve = rs.getInt("power_reserve");
            return new MechanicalWatch(id, brand, modelName, price, stock, null, imageUrl, mechanism, reserve);
        } else {
            String os = rs.getString("operating_system");
            int battery = rs.getInt("battery_capacity");
            return new Smartwatch(id, brand, modelName, price, stock, null, imageUrl, os, battery);
        }
    }
}
