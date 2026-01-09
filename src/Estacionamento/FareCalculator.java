package Estacionamento;

import Automoveis.Vehicle;

import java.time.Duration;

public class FareCalculator extends Parking {
    protected static final double BASEFARE = 5.0;
    protected static final double VALUEPERHOUR = 0.5;
    protected static final Duration DURATIONTOLERANCE = Duration.ofMinutes(10);

    public double getFare(Vehicle vehicle) {
        if (vehicle.getDuration().compareTo(DURATIONTOLERANCE) <= 0) {
            return 0;
        } else {
            Duration duration = vehicle.getDuration();
            double hoursPayable = duration.toMinutes() / 60.0;
            return BASEFARE + (VALUEPERHOUR * hoursPayable);
        }
    }
}
