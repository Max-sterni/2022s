package at.ac.uibk.pm.csaz8744.exam1.e03;

public class Procedure1 extends Cipher{

    private int key;

    public Procedure1(int key){
        this.key = key;
    }
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
            builder.append(decipherChar(c));
        }
        return  builder.toString();
    }

    private Character encipherChar(Character c){
        if(Character.isLetter(c)){
            return (char) ((c + key - 'a' + 26) % 26 + 'a');
        }
        return c;
    }

    private Character decipherChar(Character c){
        if(Character.isLetter(c)){
            return (char) ((c - key - 'a' + 26) % 26 + 'a');
        }
        return c;
    }

}
