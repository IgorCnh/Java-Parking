package Automoveis;

import java.util.Scanner;

public enum VeiculoType {
    CAR("Car"),
    MOTORCYCLE("Motorcycle"),
    TRUCK("Truck");

    private String type;

    VeiculoType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
    public String toString() {
        return this.type;
    }
}
