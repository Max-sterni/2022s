package at.ac.uibk.pm.g01.csaz8744.midterm1.e03;


public class Dealership {
    private Car[] parkingLot;
    private final int available;

    public Dealership(int available) {
        this.available = available;
        parkingLot = new Car[available];
    }

    public void addCar(Car car, int parkingSpot) throws IllegalArgumentException{
        if(parkingLot[parkingSpot] != null){
            throw new IllegalArgumentException("ParkingSpot taken");
        }
        parkingLot[parkingSpot] = car;
    }

    public void parkingLotStatus(){
        for (int i = 0; i < parkingLot.length; i++) {
            if(parkingLot[i] == null){
                System.out.println(i + ": <Empty>");
            }
            else{
                System.out.println(i + ": " + parkingLot[i].toString());
            }
        }
    }

    public void sellCar(int parkingSpot){
        if(parkingLot[parkingSpot] == null){
            throw new IllegalArgumentException("ParkingSpot empty");
        }
        parkingLot[parkingSpot] = null;
    }

    public void repark(){
        for (int i = 0; i < parkingLot.length; i++) {
            int j = i + 1;
            while (parkingLot[i] == null){
                //Empty ParkingLot guard
                if(j == parkingLot.length){
                    return;
                }

                //Swap the next car
                if (parkingLot[j] != null) {
                    parkingLot[i] = parkingLot[j];
                    parkingLot[j] = null;
                }
                j++;
            }

        }
    }
}
