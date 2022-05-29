package bank;

public class Transaction {
    private int transaction_id;
    private int source;
    private int destination;
    private int amount;
    private boolean status;

    public Transaction(int transaction_id, int source, int destination, int amount) {
        this.transaction_id = transaction_id;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
        this.status = false;
    }

    public void success() {
        status = true;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public int getAmount() {
        return amount;
    }

    public int getDestination() {
        return destination;
    }

    public int getSource() {
        return source;
    }

    public boolean getStatus() {
        return status;
    }
}
