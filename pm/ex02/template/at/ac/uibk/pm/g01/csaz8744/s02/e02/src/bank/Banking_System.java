package bank;

import java.util.ArrayList;

public class Banking_System {
    private ArrayList<Transaction> transactionLog;
    private ArrayList<Bank_Account> bank_account_list;
    private int transaction_id_counter;

    public Banking_System() {
        bank_account_list = new ArrayList<Bank_Account>();
        transactionLog = new ArrayList<Transaction>();
        transaction_id_counter = 0;
    }

    public void create_bank_account(Customer customer, int iBan) {
        Bank_Account new_account = new Bank_Account(iBan, customer);
        bank_account_list.add(new_account);
    }

    public void transfer(int source_account_iBan, int destination_account_iBan, int amount) {
        Transaction transaction = new Transaction(transaction_id_counter++, source_account_iBan,
                destination_account_iBan, amount);

        Bank_Account source_Account = getBank_Account(source_account_iBan);
        if (source_Account == null) {
            transactionLog.add(transaction);
            return;
        }

        Bank_Account destination_Account = getBank_Account(destination_account_iBan);
        if (destination_Account == null) {
            transactionLog.add(transaction);
            return;
        }

        if (source_Account.withdraw(amount)) {
            destination_Account.deposition(amount);
            transaction.success();
        }

        transactionLog.add(transaction);
        return;
    }

    private Bank_Account getBank_Account(int iBan) {
        for (Bank_Account bank_Account : bank_account_list) {
            if (bank_Account.getiBan() == iBan) {
                return bank_Account;
            }
        }

        return null;
    }

    public void printTransactions() {
        for (Transaction transaction : transactionLog) {
            System.out.println("ID: " + transaction.getTransaction_id() + " Amount " + transaction.getAmount()
                    + " from IBAN: " + transaction.getSource() + " to IBAN: " + transaction.getDestination()
                    + " Successful: " + transaction.getStatus());
        }
    }
}
