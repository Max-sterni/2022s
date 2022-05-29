package bank;

/**
 * Customer
 */
public class Customer {
    String name;
    String surname;
    Credit_rating credit_rating;

    public Customer(String name, String surname, Credit_rating credit_rating) {
        this.name = name;
        this.surname = surname;
        this.credit_rating = credit_rating;
    }

}