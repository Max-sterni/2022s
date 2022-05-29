package at.ac.uibk.pm.g01.csaz8744.s06.e02;

public class ElectoralRegion {
    
    private final String name;
    private final Integer maximumSeats;

    public ElectoralRegion(String name, Integer maximumSeats) {
        this.name = name;
        this.maximumSeats = maximumSeats;
    }

    public Integer getMaximumSeats() {
        return maximumSeats;
    }

    @Override
    public String toString() {
        return name;
    }

}
