package Program;

import Automoveis.SignValidator;
import Automoveis.Vehicle;
import Automoveis.VeiculoType;
import Estacionamento.Parking;
import Exceptions.InvalidSignException;
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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Vehicle vehicle = new Vehicle();
        SignValidator mercosulValidator = new SignValidator();
        Parking streetPark = new Parking(25);

        streetPark.entry();//VERIFICA SE EXISTEM VAGAS DISPONIVIES

        VeiculoType type = null; //VERIFICA SE O TIPO É VALIDO
        boolean istype = false;
        System.out.print("Enter vehicle type: (Car, Motorcycle or Truck) ");
        while (istype == false) {
            try {
                type = VeiculoType.valueOf(sc.nextLine().toUpperCase());
                istype = true;
            } catch (IllegalArgumentException invtype) {
                System.out.println("Invalid input.");
                System.out.print("Try again: (Car, Motorcycle or Truck) ");
            }
        }
        System.out.print("Provide the vehicle brand: "); //VERIFICA SE A MARCA É VALIDA
        String brand = null;
        while (true) {
            brand = sc.nextLine();
            if (brand.isBlank() || brand.length() <= 0) {
                System.out.println("Invalid! You need to type something!.");
                System.out.print("Type the brand here: ");
            } else {
                break;
            }
        }
        System.out.print("Provide the vehicle sign: "); //VERIFICA SE A PLACA É VALIDA
        String sign = null;
        boolean isSignCorect = false;
        while(isSignCorect==false) {
            try {
                sign = sc.nextLine().toUpperCase();
                mercosulValidator.signValidate(vehicle, sign);
                isSignCorect = true;
            } catch (InvalidSignException invsign) {
                System.out.println(invsign.getMessage());
                System.out.print("Try again (\"ABC1D23\"): ");
            }
        }
        vehicle = new Vehicle(type, brand, sign); //INSTANCIA UM NOVO VEICULO


        System.out.println("Provide the entry time: (dd/MM/yyyy HH:mm)");//VERIFICA SE A DATA DE ENTRADA É VALIDA
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

        System.out.println("It's time to choose the parking space!");
        System.out.print("Enter with the space wanted by the client: (0 - 25) ");
        int space = 0;
        boolean isSpaceValid = false;//VERIFICA SE A VAGA ESTA DISPONIVEL
        while (isSpaceValid==false) {
            try {
                space = sc.nextInt();
                streetPark.chooseParkingSpace(space, vehicle);
                isSpaceValid = true;
            } catch (IndexOutOfBoundsException invspace) {
                System.out.println(invspace.getMessage());
                System.out.print("Try again (1 - 25): ");
            }
        }
        sc.nextLine();

        System.out.println("\n\n EXIT TIME...\n\n");
        System.out.println("\nProvide the exit time: (dd/MM/yyyy HH:mm)");//VERIFICA SE A DATA É VALIDA
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
        System.out.println("So, let's calculate the time that the client been there...");//CALCULA A DURAÇAO DA "ESTADIA"
        String formatted = String.format("%02d:%02d", vehicle.getDuration().toHours(), vehicle.getDuration().toMinutes() % 60);
        System.out.println("The duration is " + formatted + "\n");
        streetPark.exit(vehicle);

        sc.close();
    }
}