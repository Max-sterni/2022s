package at.ac.uibk.pm.g01.csaz8744.s03.e06;

/**
 * Position
 */
public class Position {
    private  double longitude;
    private double latitude;
    private String name;
    

    public Position(String name, double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.name = name;
    }
    
    public double distance(Position otherPosition){
        double phi1 = Math.toRadians(this.latitude);
        double phi2 = Math.toRadians(otherPosition.latitude);

        double lambda1 = Math.toRadians(this.longitude);
        double lambda2 = Math.toRadians(otherPosition.longitude);

        double exp1 = Math.pow(Math.sin((phi2 - phi1)/2), 2);
        double exp2 = Math.pow(Math.sin((lambda2 - lambda1)/2), 2);

        return 2 * 6370 * Math.asin(Math.sqrt(exp1 + Math.cos(phi1) * Math.cos(phi2) * exp2));
    }

    
    public void print(){
        System.out.println(name + ": longitude = " + longitude + " latitude = " + latitude);
    }

}