import java.util.Stack;
/**
 * PalAlg
 */
public class PalAlg {
    public static void main(String[] args) {
        if(args.length != 1){
            System.out.println("Bad Arguments");
            System.exit(1);
        }
        System.out.println(palindrome(args[0]));
    }
    
    public static boolean palindrome(String test) {
        test = test.replaceAll("[^A-Za-z]+", "").toLowerCase();
        int length = test.length();

        if(length % 2 != 0){
            StringBuilder sBuilder = new StringBuilder(test);
            sBuilder.deleteCharAt(length/2);
            test = sBuilder.toString(); 
            length--;
        }

        Stack<Character> stk = new Stack<>();  
        
        for (int i = 0; i < length/2; i++) {
            stk.push(test.charAt(i));
        }   
        for (int i = length/2; i < length; i++) {
            if(stk.pop() != test.charAt(i)){
                return false;
            }
        }
        return true;

    }

}