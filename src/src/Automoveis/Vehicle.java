package Automoveis;

import Program.InvalidSignException;
import Program.WrongExitEntryException;

import java.time.Duration;
import java.time.LocalDateTime;

public class Vehicle {
    private VeiculoType model;
    private String brand;
    private String sign;
    private String color;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Vehicle() {
    }

    public Vehicle(VeiculoType model, String brand, String sign, String color) {
        this.model = model;
        this.brand = brand;
        this.sign = sign;
        this.color = color;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) throws InvalidSignException {
        if (SignValidator.validate(sign) == true) {
            this.sign = sign;
            System.out.println("Your vehicle has been registered!");
        } else {
            throw new InvalidSignException("Invalid sign. Try again!.");
        }
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String toString() {
        return "Model: " + model.toString() + " / Brand: " + brand + " / Sign: " + sign + " / Color: " + color;
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

}