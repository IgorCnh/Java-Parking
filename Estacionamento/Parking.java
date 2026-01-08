package Estacionamento;

import Automoveis.Vehicle;
import Automoveis.VeiculoType;
import Program.InvalidSignException;


import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Parking {
    private static int availablesParkingSpaces = 25;
    private static List<Vehicle> vehicles = new ArrayList();

    static {
        for (int i = 0; i < 25; i++) {
            vehicles.add(null);
        }
    }

    public void chooseParkingSpace(int space, Vehicle vehicle) throws InvalidSignException {
        if (space <= availablesParkingSpaces && space >= 0) {
            while (true) {
                if (vehicles.get(space) == null) {
                    vehicles.set(space, vehicle);
                    break;
                } else {
                    System.out.println("This position is already filled, please choose another.");
                    return;
                }
            }
        }
        else {
            throw new InvalidSignException("Invalid space. Try again!.");
        }
    }

    public void printSpaces(Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) != null) {
                System.out.println(vehicles.get(i).toString() + " / Position #" + i);
            }
        }
    }

    public void entry(Vehicle vehicle) throws InvalidSignException {
        if (availablesParkingSpaces > 0) {
            String sign = vehicle.getSign();
            vehicle.setSign(sign);
            System.out.println("Welcome to the parking lot!");
            availablesParkingSpaces--;
        } else {
            System.out.println("The parking lot is full, return another time...");
        }
    }
    public void exit(Vehicle vehicle) {
        availablesParkingSpaces++;
        System.out.println("Time to Exit, let's calculate your fare.\n\nSo, your fare will be 5$ + (0,5$ * Hours that your vehicle spend there.)");
        vehicles.remove(vehicle);
    }
}