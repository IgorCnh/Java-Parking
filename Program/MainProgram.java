package Program;

import Automoveis.Vehicle;
import Automoveis.VeiculoType;
import Estacionamento.Parking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Parking parking = new Parking();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Before you park, we need your vehicle data: \nProvide your vehicle type: ");
        String type = sc.nextLine().toUpperCase();
        System.out.println("Provide your vehicle brand: ");
        String brand = sc.nextLine();
        System.out.println("Provide your vehicle color: ");
        String color = sc.nextLine();
        System.out.println("Provide your vehicle sign: ");
        String sign = sc.nextLine().toUpperCase();


        Vehicle vehicle = new Vehicle(VeiculoType.valueOf(type.toUpperCase()), brand, sign, color);
        System.out.println(vehicle.toString());
        parking.entry(vehicle);
        vehicle.setSign(sign);
        System.out.println("Provide the entry time: (dd/MM/yyyy HH:mm)");
        vehicle.setEntryTime(LocalDateTime.parse(sc.nextLine(), dtf));

        System.out.println("""
                
                
                EXIT TIME...
                
                
                
                """);

        parking.exit(vehicle);
        System.out.println("Provide the exit time: (dd/MM/yyyy HH:mm)");
        vehicle.setExitTime(LocalDateTime.parse(sc.nextLine(), dtf));
        System.out.println("So, let's calculate the time that you been there...");
        System.out.println("The duration is " + vehicle.getDuration() + " - ");
        System.out.println("The total fare to pay is: $" + parking.getFare(vehicle));








        sc.close();
    }
}
