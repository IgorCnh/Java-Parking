package Estacionamento;

import Automoveis.Vehicle;

import java.time.Duration;

public interface FareCalculator { // a interface calcula o valor a pagar
    public  double calculateFare(Vehicle vehicle);
}
