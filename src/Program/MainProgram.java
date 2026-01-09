package Program;

import Automoveis.Vehicle;
import Automoveis.VeiculoType;
import Estacionamento.FareCalculator;
import Estacionamento.Parking;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Parking parking = new Parking();
        FareCalculator fareCalculator = new FareCalculator();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        VeiculoType type = null;
        Boolean istype = false;
        System.out.print("Before you park, we need your vehicle data: \nProvide your vehicle type (Car, Motorcycle or Truck): ");
        while (istype==false) {
            try {
                type = VeiculoType.valueOf(sc.nextLine().toUpperCase());
                istype = true;
            } catch (IllegalArgumentException invtype) {
                System.out.println("Invalid input.");
                System.out.print("Try again (Car, Motorcycle or Truck): ");
            }
        }
        System.out.print("Provide your vehicle brand: ");

        String brand = null;
        while (true) {
            brand = sc.nextLine();
            if (brand.isEmpty() || brand.isEmpty() || brand.length() <= 0) {
                System.out.println("Invalid! You need to type something!.");
                System.out.print("Type the brand here: ");
            }
            else  {
                break;
            }
        }
        System.out.print("Provide your vehicle color: ");
        String color = null;
        while (true) {
            color = sc.nextLine();
            if (color.isEmpty() || color.isEmpty() || color.length() <= 0) {
                System.out.println("Invalid! You need to type something!.");
                System.out.print("Type the color here: ");
            }
            else  {
                break;
            }
        }
        System.out.print("Provide your vehicle sign: ");
        String sign = null;
        boolean isSignCorect = false;
        Vehicle vehicle = new Vehicle();
        while(isSignCorect==false) {
            try {
                sign = sc.nextLine().toUpperCase();
                parking.signValidate(vehicle, sign);
                isSignCorect = true;
            } catch (InvalidSignException invsign) {
                System.out.println(invsign.getMessage());
                System.out.print("Try again (\"ABC1D23\"): ");
            }
        }
        vehicle = new Vehicle(type, brand, sign, color);
        parking.entry(vehicle);

        System.out.println("Provide the entry time: (dd/MM/yyyy HH:mm)");
        boolean isDEntryCorrect = false;
        while (isDEntryCorrect ==false) {
            try {
                LocalDateTime entryTime = LocalDateTime.parse(sc.nextLine(), dtf);
                vehicle.setEntryTime(entryTime);
                isDEntryCorrect = true;
            } catch (DateTimeException date) {
                System.out.println("Invalid date format.");
                System.out.print("Try again (dd/MM/yyyy HH:mm): ");
            } catch (WrongExitEntryException entextTime) {
                System.out.println(entextTime.getMessage());
                System.out.print("Try again (dd/MM/yyyy HH:mm): ");
            }
        }

        System.out.println("It's time to choose your parking space!");
        System.out.print("Enter the number that matches with the parking space that you want (0 - 25): ");
        int space = 0;
        boolean isSpaceValid = false;
        while (isSpaceValid==false) {
            try {
                space = sc.nextInt();
                parking.chooseParkingSpace(space, vehicle);
                isSpaceValid = true;
            } catch (IndexOutOfBoundsException invspace) {
                System.out.println(invspace.getMessage());
                System.out.print("Try again (0 - 24): ");
            }
        }
        sc.nextLine();

        parking.printData(vehicle);

        System.out.println("\n\n EXIT TIME...\n\n");
        parking.exit(vehicle);
        System.out.println("\nProvide the exit time: (dd/MM/yyyy HH:mm)");
        boolean isExitValid = false;
        while (isExitValid==false) {
            try {
                LocalDateTime exitTime = LocalDateTime.parse(sc.nextLine(), dtf);
                vehicle.setExitTime(exitTime);
                isExitValid = true;
            } catch (DateTimeException date) {
                System.out.println("Invalid date format.");
                System.out.print("Try again (dd/MM/yyyy HH:mm): ");
            } catch (WrongExitEntryException entextTime) {
                System.out.println(entextTime.getMessage());
                System.out.print("Try again (dd/MM/yyyy HH:mm): ");
            }
        }
        System.out.println("So, let's calculate the time that you been there...");
        String formatted = String.format("%02d:%02d", vehicle.getDuration().toHours(), vehicle.getDuration().toMinutes() % 60);
        System.out.println("The duration is " + formatted + "\n");
        System.out.printf("The total fare to pay is: $%.2f%n", fareCalculator.getFare(vehicle));
        if (fareCalculator.getFare(vehicle) == 0) {
            System.out.println(". You're exempt from the fare, you didn't exceed the 10 minutes tolerance");
        }
        sc.close();
    }
}