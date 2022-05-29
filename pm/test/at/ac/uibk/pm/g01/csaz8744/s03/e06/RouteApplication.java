package at.ac.uibk.pm.g01.csaz8744.s03.e06;

import javax.swing.plaf.synth.SynthStyle;

/**
 * RouteApplication
 */
public class RouteApplication {
    
    
    public static void main(String[] args) {

        Route route = new Route("Hamburg", 9.993682, 53.551086);
        route.addDestination("Innsbruck", 11.39454, 47.26266);
        route.addDestination("Paris", 2.349014, 48.864716);


        System.out.println("Start");
        System.out.println("------");
        route.getStart().print();
        for (int i = 1; i < 3; i++) {
            System.out.println("    distance = " + route.getDestination(i).distance(route.getDestination(i - 1)) + "km");
            route.getDestination(i).print();
        }
        System.out.println("------");
        System.out.println("Total distance = " + route.getTravelDistance() + "km");
    }
}