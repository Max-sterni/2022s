package at.ac.uibk.pm.g01.csaz8744.s08.e01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonManager {

    private List<Person> personList = new ArrayList<>();

    public void addPerson(Person person){
        personList.add(person);
    }

    public void printPersonList(){
        for (Person person : personList) {
            System.out.println(person);
        }
        System.out.println();
    }

    public void sortByFirstName(){
        personList.sort(new FirstNameComperator());
    }

    public void sortByLastName(){
        personList.sort(new LastNameComperator());
    }

    public void sortByBirthDate(){
        personList.sort(new BirthDateComperator());
    }

    static class FirstNameComperator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getFirstName().compareTo(p2.getFirstName());
        }
    }

    static class LastNameComperator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getLastName().compareTo(p2.getLastName());
        }
    }

    static class BirthDateComperator implements Comparator<Person> {
        @Override
        public int compare(Person p1, Person p2) {
            return p1.getBirthDate().compareTo(p2.getBirthDate());
        }
    }

    public static void main(String[] args) {
        PersonManager personManager = new PersonManager();
        personManager.addPerson(new Person("Hans", "Peter", LocalDate.of(2002, 12, 11)));
        personManager.addPerson(new Person("Rans", "Weter", LocalDate.of(2012, 8, 23)));
        personManager.addPerson(new Person("Wans", "Kreta", LocalDate.of(2002, 9, 2)));
        personManager.addPerson(new Person("Mans", "Spaeter", LocalDate.of(1999, 8, 14)));
        personManager.addPerson(new Person("Krans", "Seter", LocalDate.of(2004, 5, 8)));
        personManager.addPerson(new Person("Lans", "Schreter", LocalDate.of(1998, 1, 31)));
        personManager.addPerson(new Person("Pans", "Meta", LocalDate.of(2005, 5, 30)));
        personManager.addPerson(new Person("Ralf", "Mueller", LocalDate.of(1997, 1, 1)));

        personManager.sortByBirthDate();
        personManager.printPersonList();

        personManager.sortByFirstName();
        personManager.printPersonList();

        personManager.sortByLastName();
        personManager.printPersonList();
    }
}
