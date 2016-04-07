package CarParkSim.objects;

/**
 *
 * @author Nienke's boys
 */
public class BadParkerCar extends Car {
    
    private Location loc2;

    public BadParkerCar(){
        
    }
    
//    /**
//     *
//     * @return returns location object
//     */
//    @Override
//    public Location getLocation() {
//        return location;
//    }
    
    /**
     *
     * @return returns location object
     */
    public Location getSecondLocation() {
        return loc2;
    }

//    /**
//     *
//     * @param location location object of the car
//     */
//    @Override
//    public void setLocation(Location location) {
//        this.location = location;
//    }
    
    /**
     *
     * @param location 2nd location object of the car
     */
    public void setSecondLocation(Location location) {
        this.loc2 = location;
    }
    
    
}
