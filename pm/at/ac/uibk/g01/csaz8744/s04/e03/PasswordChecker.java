package at.ac.uibk.pm.g01.csaz8744.s04.e03;

public class PasswordChecker {
    
    class CheckingInstance{
        int length;
        int numbers;
        int lower;
        int upper;
        int special;
    }

    public PasswordStrength checkPassword(User user, String password) throws InvalidCharactersException{
        
        CheckingInstance current = new CheckingInstance();
        current.length = password.length();

        for (int i = 0; i < current.length; i++) {
            checkCharacter(password.charAt(i), current);
        }

        int rulesMet = 0;

        if(current.length >= 16){
            rulesMet++;
        }
        if (current.numbers >= 2) {
            rulesMet++;
        }
        if (current.lower >= 3) {
            rulesMet++;
        }
        if (current.upper >= 2) {
            rulesMet++;
        }
        if (current.special >= 2) {
            rulesMet++;
        }
        if(!(password.contains(user.getDay()) ||password.contains(user.getMonth()) ||password.contains(user.getYear()) ||password.contains(user.getFirst()) ||password.contains(user.getLast()))){
            rulesMet++;
        }

        if (rulesMet < 2) {
            return PasswordStrength.TOO_WEAK;
        } else if (rulesMet == 2){
            return PasswordStrength.WEAK;
        } else if (rulesMet > 2 && rulesMet < 5) {
            return PasswordStrength.MEDIUM;
        } else {
            return PasswordStrength.STRONG;
        }
        
    }

    private void checkCharacter(char c, CheckingInstance instance) throws InvalidCharactersException {
        if (Character.isDigit(c)) {
            instance.numbers++;
        }
        else if(Character.isLetter(c)){
            if(Character.isUpperCase(c)){
                instance.upper++;
            }
            else{
                instance.lower++;
            }
        }
        else if(c == '?' || c == '!' || c == '%' || c == '&' || c == '=' || c == '[' || c == ']' || c == '+' || c == '-'){
            instance.special++;
        }
        else{
            throw new InvalidCharactersException();
        }
    }
}
