package at.ac.uibk.pm.csaz8744.exam1.e03;

public class Procedure2 extends Cipher{
    @Override
    public String encipher(String message) {
        StringBuilder builder = new StringBuilder();
        for (char c : message.toLowerCase().toCharArray()) {
            builder.append(encipherChar(c));
        }
        return  builder.toString();
    }

    @Override
    public String decipher(String message) {
        StringBuilder builder = new StringBuilder();
        for (char c : message.toLowerCase().toCharArray()) {
            builder.append(encipherChar(c));
        }
        return  builder.toString();
    }

    private static Character encipherChar(Character c){
        if(Character.isLetter(c)){
            return (char) (26 - (c - 'a' + 1) + 'a');
        }
        return c;
    }
}
