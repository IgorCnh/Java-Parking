package Automoveis;

public class SignValidator {
    public static boolean validate(String sign){
        if(sign.length() != 7){
            return false;
        }

        boolean containsLetter1= false;
        boolean containsLetter2= false;
        boolean containsLetter3= false;
        boolean containsDigit1 = false;
        boolean containsLetter4= false;
        boolean containsDigit2= false;
        boolean containsDigit3= false;


        for(int i = 0; i < sign.length(); i++){
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
        }
        return (containsLetter1 && containsLetter2 && containsLetter3 && containsLetter4 && containsDigit1 && containsDigit2 && containsDigit3);
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