package Estacionamento;

import Automoveis.Vehicle;


import java.time.Duration;
import java.util.*;

public class Parking implements FareCalculator {
    private static int availablesParkingSpaces;
    private static Map<Integer, Vehicle> mapVehicle;

    public Parking(int capacity) {
        availablesParkingSpaces = capacity;
        mapVehicle = new TreeMap<>();
    }


    public void chooseParkingSpace(int space, Vehicle vehicle) throws IndexOutOfBoundsException {
        if ((space <= availablesParkingSpaces) && (space >= 1)) { //verifica se o input da vaga foi válido (o estacionamento tem x vagas)
            if (!mapVehicle.containsKey(space)) { //verifica se a vaga esta vazia / não tem outro com a mesma key
                mapVehicle.put(space, vehicle);
                availablesParkingSpaces--;
            } else {
                System.out.println("This position is already filled, ask for another position.");
            }
        }
        else {
            throw new IndexOutOfBoundsException("Invalid space. Try again!.");
        }
    }

    public void entry() { //adiciona o veicula ao estacionamento
        if (availablesParkingSpaces > 0) { //verifica se o estacionamento possui vagas disponiveis
            System.out.println("There are available parking spaces, start the vehicle registration");
        } else {
            System.out.println("The parking lot is full...");
        }
    }
    @Override
    public double calcularFare(Vehicle vehicle) {
        System.out.println("Calculating Fare...");
        if (vehicle.getDuration().compareTo(Duration.ofMinutes(10)) < 0){ // verifica se a duração é menor que a tolerancia
            double fare = 0;
            System.out.println("The client didn't take long, he is exempt of fare.");
            return fare;
        }
        else{ //caso o contrario, deve se pagar a taxa de 5$ + 0.5 * a quantidade de horas gastas no estacionamento
            double fare = 5 + 0.5 * (vehicle.getDuration().toMinutes() / 60.0);
            System.out.print("The client's fare will be: " + String.format("%.2f", fare));
            return fare;
        }
    }
    public void exit(Vehicle vehicle) { //retira o veiculo do estacionamento
        System.out.println("Time to Exit. Let's calculate the client's fare.");
        calcularFare(vehicle);
        mapVehicle.values().remove(vehicle);
        availablesParkingSpaces++;
    }
}