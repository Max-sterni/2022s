package at.ac.uibk.pm.g01.csaz8744.s08.e01;

import java.time.LocalDate;

public class Person {

    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;

    public Person(String firstName, String lastName, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + birthDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}
