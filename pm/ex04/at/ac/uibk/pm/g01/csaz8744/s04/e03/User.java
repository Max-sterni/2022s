package at.ac.uibk.pm.g01.csaz8744.s04.e03;

public class User {
    
    String first;
    String last;
    String day;
    String month;
    String year;


    public User(String first, String last, int day, int month, int year) {
        this.first = first;
        this.last = last;
        this.day = Integer.toString(day);
        this.month = Integer.toString(month);
        this.year = Integer.toString(year);
    }

    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = Integer.toString(day);
    }

    public String getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = Integer.toString(month);
    }

    public String getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = Integer.toString(year);
    }
    

}
