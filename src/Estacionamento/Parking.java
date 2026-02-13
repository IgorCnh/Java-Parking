package Estacionamento;

import Automoveis.Vehicle;
import Exceptions.InvalidSpaceException;
import Interfaces.FareCalculator;


import java.time.Duration;
import java.util.*;

public class Parking implements FareCalculator {
    private static int availablesParkingSpaces;
    private static Map<Integer, Vehicle> mapVehicle;

    public Parking(int capacity) {
        availablesParkingSpaces = capacity;
        mapVehicle = new TreeMap<>();
    }
    public void occupySpace(int space, Vehicle vehicle) throws IndexOutOfBoundsException{
        mapVehicle.put(space, vehicle);
    }
    public void parkingEntry() { //adiciona o veicula ao estacionamento
        verifyIfHaveSpace();
    }
    @Override
    public void calculateFare(Vehicle vehicle) {
        System.out.println("Calculating Fare...");
        if (isInToleranceTime(vehicle)){
            System.out.println("The client didn't take long, he is exempt of fare.");
        }
        else{
            double fare = 5 + 0.5 * (vehicle.getDuration().toMinutes() / 60.0);
            System.out.print("The client's fare will be: " + String.format("%.2f", fare));
        }
    }
    public void parkingExit(Vehicle vehicle) { //retira o veiculo do estacionamento
        mapVehicle.values().remove(vehicle);
        availablesParkingSpaces++;
    }
    public boolean isInToleranceTime(Vehicle vehicle) {
        if (vehicle.getDuration().compareTo(Duration.ofMinutes(10)) < 0){
            return true;
        }
        return false;
    }
    private void verifyIfHaveSpace() {
        if (availablesParkingSpaces > 0) { //verifica se o estacionamento possui vagas disponiveis
            System.out.println("There are available parking spaces, start the vehicle registration");
        } else {
            System.out.println("The parking lot is full...");
        }
    }
    public boolean isSpaceFilled(int space) throws InvalidSpaceException {
        if (mapVehicle.get(space) == null) {
            return false;
        }
        else  {
            throw new InvalidSpaceException("Filled space. Choose another one!");
        }
    }
}