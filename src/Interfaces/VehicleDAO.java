package Interfaces;

import Automoveis.Vehicle;

import java.util.List;

public interface VehicleDAO {
    public void insertVehicle(Vehicle vehicle);
    public void updateVehicle(Vehicle vehicle);
    public void deleteVehicle(Vehicle vehicle);
    public Vehicle getVehicle(String sign);
    public List<Vehicle> getAllVehicles();
}
