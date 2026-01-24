package Estacionamento;

import Automoveis.Vehicle;

import java.awt.desktop.ScreenSleepEvent;
import java.time.Duration;

public interface FareCalculator { // a interface calcula o valor a pagar
    public  double calcularFare(Vehicle vehicle);
}
