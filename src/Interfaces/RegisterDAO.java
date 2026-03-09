package Interfaces;

import Automoveis.Vehicle;

public interface RegisterDAO {
    public void insertVehicleRegister(Vehicle vehicle);
    public void updateVehicleRegister(int registerID, Vehicle vehicle);
    public void deleteVehicleRegister(Vehicle vehicle);

}
