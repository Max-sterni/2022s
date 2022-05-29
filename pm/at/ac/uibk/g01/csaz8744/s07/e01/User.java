package at.ac.uibk.pm.g01.csaz8744.s07.e01;

/**
 * User
 */
public class User implements Identifiable<String>{

    private final String username;
    private final String name;
    private final String surname;

    public User(String username, String name, String surname) {
        this.username = username;
        this.name = name;
        this.surname = surname;
    }

    public String getUniqueIdentifier() {
        return username;
    }

    @Override
    public String toString() {
        return username + ": " + name + " " + surname;
    }
}