package Estacionamento;

import Automoveis.Vehicle;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Parking {
private static int AvailablesParkingSpaces = 25;
private static List<Vehicle> vehicles = new ArrayList();
private static final double BASEFARE = 5.0;
private static final double VALUEPERHOUR = 0.5;

public void addVehicle(Vehicle vehicle) {
    vehicles.add(vehicle);
}

public void removeVehicle(Vehicle vehicle) {
    vehicles.remove(vehicle);
}

public void entry(Vehicle vehicle){
    if(AvailablesParkingSpaces>0) {
        System.out.println("Welcome to the parking lot!");
        AvailablesParkingSpaces--;
        addVehicle(vehicle);
    }
    else{
        System.out.println("The parking lot is full, return another time...");
    }
}
public void exit(Vehicle vehicle){
    AvailablesParkingSpaces++;
    System.out.println("Time to Exit, let's calculate your fare.\n\nSo, your fare will be 5$ + (0,5$ * Hours that your vehicle spend there.)");
    removeVehicle(vehicle);
}
public double getFare(Vehicle vehicle){
    Duration duration = vehicle.getDuration();
    double hoursPayable = duration.toMinutes() / 60.0;
    return BASEFARE + (VALUEPERHOUR * hoursPayable);
}

}
