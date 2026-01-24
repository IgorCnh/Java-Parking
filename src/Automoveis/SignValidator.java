package Automoveis;

import Exceptions.InvalidSignException;

public class SignValidator { //A classe signValidator valida a placa do carro no padrão MERCOSUL
    public static boolean validate(String sign){
        if(sign.length() != 7){ //Não existe placa menor de 7 digitos
            return false;
        }

        boolean containsLetter1= false;
        boolean containsLetter2= false;
        boolean containsLetter3= false;
        boolean containsDigit1 = false;
        boolean containsLetter4= false;
        boolean containsDigit2= false;
        boolean containsDigit3= false;


        for(int i = 0; i < sign.length(); i++){ //verifica se o padrão ABC1D23 existe na placa digitada pelo user
            char c = sign.charAt(i);
            switch (i) {
                case 0: containsLetter1 = Character.isLetter(c);
                case 1: containsLetter2 = Character.isLetter(c);
                case 2: containsLetter3 = Character.isLetter(c);
                case 3: containsDigit1  = Character.isDigit(c);
                case 4: containsLetter4 = Character.isLetter(c);
                case 5: containsDigit2  = Character.isDigit(c);
                case 6: containsDigit3  = Character.isDigit(c);
            }
        } //Caso a placa preencha todos os campos corretamente, retorna true
        return (containsLetter1 && containsLetter2 && containsLetter3 && containsLetter4 && containsDigit1 && containsDigit2 && containsDigit3);
    }
    public void signValidate(Vehicle vehicle, String sign) throws InvalidSignException {
        if (SignValidator.validate(sign) == true) {
            vehicle.setSign(sign); //implementa o sistema de verificação de placa no sistema principal
        }
        else {
            throw new InvalidSignException("Invalid sign. Try again!.");
        }
    }
}

/**
 * CLASSE PRINCIPAL PARA VALIDAR PLACA:
 *
 * public class MainProgram {
 *     public static void main(String[] args) {
 *         Locale.setDefault(Locale.US);
 *         Scanner sc = new Scanner(System.in);
 *
 *         Vehicle v = new Vehicle();
 *         String sign = sc.nextLine().toUpperCase(Locale.US);
 *         v.setSign(sign);
 *         System.out.println(v.getSign());
 *         sc.close();
 *     }
 * }
 */