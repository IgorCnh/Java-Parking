package Program;

import Automoveis.MercosulSignValidator;
import Automoveis.Vehicle;
import Automoveis.VeiculoType;
import Estacionamento.Parking;
import Exceptions.InvalidSignException;
import Exceptions.InvalidSpaceException;
import Exceptions.WrongExitEntryException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Parking streetPark = new Parking(25);
        streetPark.parkingEntry();
        System.out.print("Enter vehicle type: (Car, Motorcycle or Truck) ");
        VeiculoType type = typeVerifier(sc);
        System.out.print("Provide the vehicle sign: ");
        String sign = signVerifier(sc);
        Vehicle vehicle = new Vehicle(type, sign);
        System.out.println("Provide the entry time: (dd/MM/yyyy HH:mm)");
        entryDateTimeVerifier(sc, vehicle);
        System.out.println("It's time to choose the parking space!");
        System.out.print("Enter with the space wanted by the client: (0 - 25) ");
        int space = parkingSpaceVerifier(sc, streetPark);
        streetPark.occupySpace(space, vehicle);

        System.out.println("\n\n EXIT TIME...\n\n");

        System.out.println("\nProvide the exit time: (dd/MM/yyyy HH:mm)");
        exitDateTimeVerifier(sc, vehicle);
        System.out.println("So, let's calculate the time that the client been there...");
        String formatted = String.format("%02d:%02d\n", vehicle.getDuration().toHours(), vehicle.getDuration().toMinutes() % 60);
        System.out.printf("The duration is %sh", formatted);
        streetPark.calculateFare(vehicle);

        streetPark.parkingExit(vehicle);
        sc.close();
    }

    //FUNçOES

    public final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static VeiculoType typeVerifier(Scanner sc) {
        while (true) {
            try {
                VeiculoType type = VeiculoType.valueOf(sc.nextLine().toUpperCase());
                return type;
            } catch (IllegalArgumentException invtype) {
                System.out.println("Invalid input.");
                System.out.print("Try again: (Car, Motorcycle or Truck) ");
            }
        }
    }

    public static String signVerifier(Scanner sc) {
        MercosulSignValidator mercosulValidator = new MercosulSignValidator();
        while (true) {
            try {
                String sign = sc.nextLine().toUpperCase();
                mercosulValidator.isSignValid(sign);
                return sign;
            } catch (InvalidSignException invsign) {
                System.out.println(invsign.getMessage());
                System.out.print("Try again (\"ABC1D23\"): ");
            }
        }
    }

    public static LocalDateTime entryDateTimeVerifier(Scanner sc, Vehicle vehicle) {
        while (true) {
            try {
                LocalDateTime entryTime = LocalDateTime.parse(sc.nextLine(), FORMATTER);
                vehicle.setEntryTime(entryTime);
                return entryTime;
            } catch (DateTimeException date) {
                System.out.println("Invalid date format.");
                System.out.print("Try again (dd/MM/yyyy HH:mm): ");
            }
        }
    }

    public static LocalDateTime exitDateTimeVerifier(Scanner sc, Vehicle vehicle) {
        while (true) {
            try {
                LocalDateTime exitTime = LocalDateTime.parse(sc.nextLine(), FORMATTER);
                vehicle.setExitTime(exitTime);
                return exitTime;
            }catch (DateTimeException date) {
                System.out.println("Invalid date format.");
            }catch (WrongExitEntryException exitexc){
                System.out.println(exitexc.getMessage());
            }
        }
    }

    public static int parkingSpaceVerifier(Scanner sc, Parking parking) {
        while (true) {
            try {
                int space = sc.nextInt();
                sc.nextLine();
                parking.isSpaceFilled(space);
                return space;
            } catch (IndexOutOfBoundsException invspace) {
                System.out.println(invspace.getMessage());
                System.out.print("Try again (1 - 25): ");
            }catch (InvalidSpaceException invspace) {
                System.out.println(invspace.getMessage());
            }
        }
    }
}