package bank;

public class Bank_Account {
    private int iBan;
    private Customer owner;
    private int balance;

    public Bank_Account(int iBan, Customer owner) {
        this.iBan = iBan;
        this.owner = owner;
        this.balance = 0;
    }

    public void deposition(int amount) {
        balance += amount;
    }

    public boolean withdraw(int amount) {
        if (withdrawCheck(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean withdrawCheck(int amount) {
        switch (owner.credit_rating) {
            case LOW:
                return balance - amount >= -100;

            case MEDIUM:
                return balance - amount >= -500;

            case HIGH:
                return balance - amount >= -1000;

            default:
                return false;
        }
    }

    public int getiBan() {
        return iBan;
    }

}
