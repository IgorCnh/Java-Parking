package Automoveis;

import Exceptions.InvalidSignException;
import Interfaces.SignValidate;

public class MercosulSignValidator implements SignValidate { //A classe signValidator valida a placa do carro no padrão MERCOSUL
    @Override
    public boolean signValidator(String sign){
        if (sign.length() != 7) { //Não existe placa menor de 7 digitos
            return false;
        }
        boolean containsLetter1 = false;
        boolean containsLetter2 = false;
        boolean containsLetter3 = false;
        boolean containsDigit1 = false;
        boolean containsLetter4 = false;
        boolean containsDigit2 = false;
        boolean containsDigit3 = false;
        boolean isValid = false;
        for (int i = 0; i < sign.length(); i++) {
            char c = sign.charAt(i);
            switch (i) {
                case 0:
                    containsLetter1 = Character.isLetter(c);
                case 1:
                    containsLetter2 = Character.isLetter(c);
                case 2:
                    containsLetter3 = Character.isLetter(c);
                case 3:
                    containsDigit1 = Character.isDigit(c);
                case 4:
                    containsLetter4 = Character.isLetter(c);
                case 5:
                    containsDigit2 = Character.isDigit(c);
                case 6:
                    containsDigit3 = Character.isDigit(c);
            }
            isValid = containsLetter1 && containsLetter2 && containsLetter3 && containsLetter4 && containsDigit1 && containsDigit2 && containsDigit3;
        }
        return (isValid);
    }
    public void isSignValid(String sign) throws InvalidSignException {
       if(signValidator(sign) == false) {
            throw new InvalidSignException("Invalid sign.");
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