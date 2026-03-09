package Automoveis;

import Interfaces.RegisterDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class RegisterDaoJDBC implements RegisterDAO {
    private Connection conn;
    public RegisterDaoJDBC(Connection conn) {
        this.conn = conn;
    }
    @Override
    public void insertVehicleRegister(Vehicle vehicle) {
        String query = "INSERT INTO registers (entryTime, vehicleSign) VALUES(?, ?)";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setTimestamp(1, Timestamp.valueOf(vehicle.getEntryTime()));
            ps.setString(2, vehicle.getSign());
            ps.executeUpdate();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void updateVehicleRegister(int registerID, Vehicle vehicle) {
        String query = "UPDATE registers SET entryTime = ?, exitTime = ? WHERE registerID = ? AND vehicleSign = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setTimestamp(1, Timestamp.valueOf(vehicle.getEntryTime()));
            ps.setTimestamp(2, Timestamp.valueOf(vehicle.getExitTime()));
            ps.setInt(3, registerID);
            ps.setString(4, vehicle.getSign());
            ps.executeUpdate();
        }catch (SQLException e){
            e.getMessage();
        }
    }

    @Override
    public void deleteVehicleRegister(Vehicle vehicle) {

    }
}
