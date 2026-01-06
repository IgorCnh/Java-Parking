package Program;

import Automoveis.Vehicle;
import Automoveis.VeiculoType;
import Estacionamento.FareCalculator;
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
        FareCalculator fareCalculator = new FareCalculator();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.print("Before you park, we need your vehicle data: \nProvide your vehicle type: ");
        String type = sc.nextLine().toUpperCase();
        System.out.print("Provide your vehicle brand: ");
        String brand = sc.nextLine();
        System.out.print("Provide your vehicle color: ");
        String color = sc.nextLine();
        System.out.print("Provide your vehicle sign: ");
        String sign = sc.nextLine().toUpperCase();


        Vehicle vehicle = new Vehicle(VeiculoType.valueOf(type.toUpperCase()), brand, sign, color);
        parking.entry(vehicle);
        vehicle.setSign(sign);
        System.out.println("Provide the entry time: (dd/MM/yyyy HH:mm)");
        vehicle.setEntryTime(LocalDateTime.parse(sc.nextLine(), dtf));

        System.out.println("It's time to choose your parking space!");
        System.out.print("Enter the number that matches with the parking space that you want: ");
        int space = sc.nextInt();
        sc.nextLine();
        parking.chooseParkingSpace(space, vehicle);

        parking.printSpaces(vehicle);





        System.out.println("""
                
                
                EXIT TIME...
                
                
                
                """);

        parking.exit(vehicle);
        System.out.println("\nProvide the exit time: (dd/MM/yyyy HH:mm)");
        vehicle.setExitTime(LocalDateTime.parse(sc.nextLine(), dtf));
        System.out.println("So, let's calculate the time that you been there...");
        String formatted = String.format("%02d:%02d", vehicle.getDuration().toHours(),vehicle.getDuration().toMinutes() % 60);
        System.out.println("The duration is " + formatted +"\n");
        System.out.printf("The total fare to pay is: $%.2f%n", fareCalculator.getFare(vehicle));
        if (fareCalculator.getFare(vehicle) == 0){
            System.out.println(". You're isent of fare, you didn't exceed the 10 minutes tolerance");
        }








        sc.close();
    }
}
