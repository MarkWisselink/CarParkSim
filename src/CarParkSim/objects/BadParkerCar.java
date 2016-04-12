package CarParkSim.objects;

/**
 *
 * @author Nienke's boys
 */
public class BadParkerCar extends Car {

    private Location loc2;

    /**
     * create a bad parker, this car uses 2 parking spots<br>
     * has 2 additional methods, neither of which exist in the super class Car
     */
    public BadParkerCar() {

    }

    /**
     *
     * @return returns location object
     */
    public Location getSecondLocation() {
        return loc2;
    }

    /**
     *
     * @param location 2nd location object of the car
     */
    public void setSecondLocation(Location location) {
        this.loc2 = location;
    }
}
