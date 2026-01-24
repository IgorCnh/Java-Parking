package Automoveis;

import Exceptions.InvalidSignException;
import Exceptions.WrongExitEntryException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Vehicle {
    private VeiculoType model;
    private String brand;
    private String sign;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Vehicle() {
    }

    public Vehicle(VeiculoType model, String brand, String sign) {
        this.model = model;
        this.brand = brand;
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) throws InvalidSignException {
        this.sign = sign;
    }

    public VeiculoType getModel() {
        return model;
    }

    public void setModel(VeiculoType model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String toString() {
        return "Model: " + model.toString() + " / Brand: " + brand + " / Sign: " + sign;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) throws WrongExitEntryException {
        if (entryTime != null) {
            this.entryTime = entryTime;
        } else {
            throw new WrongExitEntryException("Invalid entry time. The entry can't be after the exit time!");
        }
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