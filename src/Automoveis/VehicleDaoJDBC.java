package Automoveis;

import Exceptions.InvalidSignException;
import Exceptions.WrongExitEntryException;
import Interfaces.VehicleDAO;

import java.sql.*;
import java.util.List;

public class VehicleDaoJDBC implements VehicleDAO {
    private Connection conn;
    public VehicleDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insertVehicle(Vehicle vehicle) {
        String query = "INSERT INTO parking (sign, type, entryTime, exitTime) VALUES (?, ?, ?, null)";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, vehicle.getSign());
            ps.setString(2, String.valueOf(vehicle.getType()));
            ps.setTimestamp(3, Timestamp.valueOf(vehicle.getEntryTime()));

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {

        String query = "UPDATE parking SET sign=?, type=?, entryTime=?, exitTime=? WHERE sign=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, vehicle.getSign());
            ps.setString(2, String.valueOf(vehicle.getType()));
            ps.setTimestamp(3, Timestamp.valueOf(vehicle.getEntryTime()));
            ps.setTimestamp(4, Timestamp.valueOf(vehicle.getExitTime()));
            ps.setString(5, vehicle.getSign());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void deleteVehicle(Vehicle vehicle) {
        String query = "DELETE FROM parking WHERE sign=?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setString(1, vehicle.getSign());
            ps.executeUpdate();
    }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Vehicle getVehicle(String sign) {

        String query = "SELECT * FROM parking WHERE sign=?";

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, sign);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                Vehicle vehicle = new Vehicle();

                vehicle.setSign(rs.getString("sign"));
                vehicle.setType(VeiculoType.valueOf(rs.getString("type")));
                vehicle.setEntryTime(rs.getTimestamp("entryTime").toLocalDateTime());
                vehicle.setExitTime(rs.getTimestamp("exitTime").toLocalDateTime());

                return vehicle;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InvalidSignException e) {
            throw new RuntimeException(e);
        } catch (WrongExitEntryException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return List.of();
    }
}
