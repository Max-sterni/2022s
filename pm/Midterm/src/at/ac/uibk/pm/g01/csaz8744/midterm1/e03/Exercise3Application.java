package at.ac.uibk.pm.g01.csaz8744.midterm1.e03;


import java.util.Collection;

public class Exercise3Application {
    public static void main(String[] args) {
        Dealership demoDealership = new Dealership(9);

        demoDealership.addCar(new Car("HH SK 990"), 1);
        demoDealership.addCar(new Car("B PL 8891", Condition.GOOD_AS_NEW, 89), 4);
        demoDealership.addCar(new Car("OGL MM 1969", Condition.OBVIOUS_SIGNS_OF_WEAR, 300000), 8);

        demoDealership.parkingLotStatus();
        System.out.println();

        demoDealership.repark();

        demoDealership.parkingLotStatus();
        System.out.println();

        demoDealership.sellCar(0);

        demoDealership.parkingLotStatus();

    }
}
