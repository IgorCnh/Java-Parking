package Automoveis;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;

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
    public void setSign(String sign) {
        if(SignValidator.validate(sign) == true){
            this.sign = sign;
            System.out.println("Your vehicle has been registered!");
        }
        else{
            throw new IllegalArgumentException("Invalid sign. Try again!.");
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
    public String toString(){
        return "Model: " + model.toString() + " / Brand: " + brand + " / Sign: " + sign + " / Color: " + color;
    }
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }
    public LocalDateTime getExitTime() {
        return exitTime;
    }
    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
    public Duration getDuration(){
        return Duration.between(entryTime, exitTime);
    }

}