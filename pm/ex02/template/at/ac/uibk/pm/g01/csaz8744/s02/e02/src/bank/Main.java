package bank;

public class Main {
    public static void main(String[] args) {
        Customer person1 = new Customer("Harald", "Marald", Credit_rating.LOW);
        Customer person2 = new Customer("Lara", "Mara", Credit_rating.HIGH);

        Banking_System banking_System = new Banking_System();

        banking_System.create_bank_account(person1, 1234);
        banking_System.create_bank_account(person2, 4321);

        banking_System.printTransactions();
        System.out.println();

        banking_System.transfer(1234, 4321, 120);
        banking_System.transfer(4321, 1234, 200);
        banking_System.transfer(1234, 4321, 120);

        banking_System.printTransactions();
    }
}
