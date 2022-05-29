package at.ac.uibk.pm.g01.csaz8744.midterm1.e03;

public class Car {

    private final String serialNumber;
    private final Condition condition;
    private final int mileage;

    public Car(String serialNumber){
        this.serialNumber = serialNumber;
        this.condition = Condition.NEW;
        this.mileage = 0;
    }

    public Car(String serialNumber, Condition condition, int mileage) throws IllegalArgumentException{

        this.serialNumber = serialNumber;
        this.condition = condition;
        this.mileage = mileage;

        //Argument guards
        if(mileage < 0){
            throw new IllegalArgumentException("Mileage can't be negative");
        }
        if(condition == Condition.NEW && mileage != 0){
            throw new IllegalArgumentException("New car can't have mileage greater then zero");
        }
    }

    @Override
    public String toString() {
        return serialNumber + " (" + condition + ", " + mileage + "km)";
    }
}
