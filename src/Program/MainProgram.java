package Program;

import Automoveis.MercosulSignValidator;
import Automoveis.Vehicle;
import Automoveis.VehicleDaoJDBC;
import Automoveis.VeiculoType;
import Estacionamento.Parking;
import Exceptions.InvalidSignException;
import Exceptions.InvalidSpaceException;
import Exceptions.WrongExitEntryException;
import DB.DB;
import Interfaces.VehicleDAO;

import java.sql.Connection;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class MainProgram {
    public static void main(String[] args) {
        Connection conn = DB.getConnection();
        VehicleDAO dao = new VehicleDaoJDBC(conn);
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Parking streetPark = new Parking(25);
        streetPark.parkingEntry();
        System.out.print("Enter vehicle type: (Car, Motorcycle or Truck) ");
        VeiculoType type = typeVerifier(sc);
        System.out.print("Provide the vehicle sign: ");
        String sign = signVerifier(sc, conn);
        Vehicle vehicle = new Vehicle(type, sign.toUpperCase());
        System.out.println("Entry time registered!");
        entryDateTimeVerifier(vehicle);
        dao.insertVehicle(vehicle);
        System.out.println("It's time to choose the parking space!");
        System.out.print("Enter with the space wanted by the client: (0 - 25) ");
        int space = parkingSpaceVerifier(sc, streetPark);
        streetPark.occupySpace(space, vehicle);

        System.out.println("Do you want to continue (y/n): ");
        char answer = sc.next().charAt(0);
        if (answer == 'n') {
            System.out.println("Thank you for using this program!");
            System.exit(0);
        }

        System.out.println("\n\n EXIT TIME...\n\n");

        System.out.println("\nProvide the exit time: (dd/MM/yyyy HH:mm)");
        exitDateTimeVerifier(sc, vehicle);
        dao.updateVehicle(vehicle);
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

    public static String signVerifier(Scanner sc, Connection conn) {
        MercosulSignValidator mercosulValidator = new MercosulSignValidator();
        VehicleDaoJDBC dao = new VehicleDaoJDBC(conn);
        while (true) {
            try {
                String sign = sc.next().toUpperCase();
                mercosulValidator.isSignValid(sign);
                if(dao.signAlreadyExists(sign.toUpperCase()) == true){
                    System.out.println("Sign already exists. " + "\ntype again('ABC1D23'): ");
                }else {
                    return sign;
                }
            } catch (InvalidSignException invsign) {
                System.out.println(invsign.getMessage());
                System.out.print("Try again ('ABC1D23'): ");
            }
        }
    }

    public static LocalDateTime entryDateTimeVerifier(Vehicle vehicle) {
        while (true) {
            try {
                LocalDateTime entryTime = LocalDateTime.now()
                                .withSecond(0)
                                .withNano(0);
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