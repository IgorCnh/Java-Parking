package Estacionamento;

import Automoveis.Vehicle;
import Program.InvalidSignException;
import Program.InvalidSpaceException;


import java.util.ArrayList;
import java.util.List;

public class Parking {
    private static int availablesParkingSpaces = 25;
    private static List<Vehicle> vehicles = new ArrayList();

    static { // inicia a lista de vagas com 25 vagas disponíveis (cria com 26, porem a vaga sera descartada com uma exceção
        for (int i = 0; i < 25; i++) {
            vehicles.add(null);
        }
    }

    public void chooseParkingSpace(int space, Vehicle vehicle) throws IndexOutOfBoundsException {
        if ((space <= 24) && (space >= 1)) {
            if (vehicles.get(space) == null) {
                vehicles.set(space, vehicle);
            } else {
                System.out.println("This position is already filled, please choose another.");
            }
        }
        else {
            throw new IndexOutOfBoundsException("Invalid space. Try again!.");
        }
    }

    public void printData(Vehicle vehicle) {
        for (int i = 0; i < vehicles.size(); i++) {
            if (vehicles.get(i) != null) {
                System.out.println("VEHICLE REGISTER:\n" + vehicles.get(i).toString() + " / Position #" + i);
            }
        }
    }

    public void entry(Vehicle vehicle) {
        if (availablesParkingSpaces > 0) {
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
    public void signValidate(Vehicle vehicle, String sign) throws InvalidSignException {
        vehicle.setSign(sign);
    }
}