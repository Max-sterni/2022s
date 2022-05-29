package at.ac.uibk.pm.g01.csaz8744.s03.e06;

import java.util.ArrayList;

/**
 * Route
 */
public class Route {

    private ArrayList<Position> destination_list = new ArrayList<>();

    public Route(String start_name, double start_longitude, double start_latitude){
        addDestination(start_name, start_longitude, start_latitude);
    }

    public void addDestination(String name, double longitude, double latitude) {
        destination_list.add(new Position(name, longitude, latitude));
    }

    public Position getStart() {
        return destination_list.get(0);
    }

    public Position getDestination(int number) {
        return destination_list.get(number);
    }

    public double getTravelDistance() {
        double distance = 0;
        Position first = getStart();
        for (int i = 1; i < destination_list.size(); i++) {
            distance += first.distance(destination_list.get(i));
            first = destination_list.get(i);
        }
        return distance;
    }

}