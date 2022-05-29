package at.ac.uibk.pm.g01.csaz8744.s05.e03;

public class Person {

    private String firstname;
    private String lastname;
    private Address address;
    private String dateOfBirth;
    private String[] hobbies;

    public Person(String firstname, String lastname, Address address, String dateOfBirth, String[] hobbies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.hobbies = hobbies;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(firstname + " " + lastname);
        stringBuilder.append(" Adress: " + address);
        stringBuilder.append(" Hobbies: ");
        for (String string : hobbies) {
            stringBuilder.append(string + ", ");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        
        return stringBuilder.toString();
    }
}
