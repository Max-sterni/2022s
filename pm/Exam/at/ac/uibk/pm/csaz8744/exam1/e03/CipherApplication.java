package at.ac.uibk.pm.csaz8744.exam1.e03;

public class CipherApplication {

    public static String demo(String message, Cipher cipher){
        String enciphered = cipher.encipher(message);
        String deciphered = cipher.decipher(enciphered);
        return String.format("%s -> %s -> %s", message, enciphered, deciphered);
    }

    public static void main(String[] args) {
        Cipher one = new Procedure1(4);
        Cipher two = new Procedure2();

        System.out.println(demo("Das Quiz war sehr schwer", one));

        System.out.println(demo("Das Quiz war sehr schwer", two));
    }
}
