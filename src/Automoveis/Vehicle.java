package Automoveis;

import Exceptions.InvalidSignException;
import Exceptions.WrongExitEntryException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Scanner;

public class Vehicle {
    private VeiculoType type;
    private String brand;
    private String sign;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Vehicle() {
    }
    public Vehicle(VeiculoType type, String sign) {
        this.type = type;
        this.sign = sign;
    }
    public String getSign() {
        return sign;
    }
    public void setSign(String sign) throws InvalidSignException {
        this.sign = sign;
    }
    public VeiculoType getType() {
        return type;
    }
    public void setType(VeiculoType type) {

    }
    public String toString() {
        return "Model: " + type.toString() + " / Sign: " + sign;
    }
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(LocalDateTime entryTime){
        this.entryTime = entryTime;
    }
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    public void setExitTime(LocalDateTime exitTime) throws WrongExitEntryException {
        if (exitTime.isAfter(entryTime)) {
            this.exitTime = exitTime;
        } else {
            throw new WrongExitEntryException("Invalid entry time. the exit time can't be before the entry time!");
        }
    }
    public Duration getDuration() {
        return Duration.between(entryTime, exitTime);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(sign, vehicle.sign);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(sign);
    }
}