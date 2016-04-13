package CarParkSim.objects;

import java.util.*;
import CarParkSim.logic.*;

/**
 *
 * @author Nienke's boys
 */
public class LocationGrid {

    private HashMap<String, Integer> stateMap;
    private HashMap<String, Car> carMap;
    private Model model;

    /**
     *
     * @param model the model using this grid
     */
    public LocationGrid(Model model) {
        this.model = model;
        stateMap = new HashMap<>();
        carMap = new HashMap<>();
    }

    /**
     * @param skipFirst if you want to skip the first result<br>
     * true: skips the first; false: returns on the first result
     * @return Location object for a free location
     */
    public Location getFirstFreeLocation(boolean skipFirst) {
        boolean first = skipFirst;
        for (int floor = 0; floor < model.getNumFloors(); floor++) {
            for (int row = 0; row < model.getNumRows(); row++) {
                for (int place = 0; place < model.getNumPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getLocationState(location) == 0) {
                        if (first) {
                            first = false;
                        }
                        else {
                            return location;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     *
     * @return gets the first free location
     */
    public Location getFirstFreeLocation() {
        return getFirstFreeLocation(false);
    }

    /**
     *
     * @param loc Location
     * @param state int value of the state<br>
     * 0= empty<br>
     * 1= taken<br>
     * 2= taken by bad parker<br>
     * 3= taken by passholder<br>
     * 4= taken by reserving car<br>
     * 12= taken as bad parker's 2nd place<br>
     * 14= reserved by a car/not yet arrived
     */
    public void setLocationState(Location loc, Integer state) {
        if (state == 0) {
            stateMap.remove(loc.toString());
        }
        else if (state == null) {
            stateMap.remove(loc.toString());
        }
        else {
            stateMap.put(loc.toString(), state);
        }
    }

    /**
     *
     * @param loc Location
     * @return int value of the state<br>
     * 0= empty<br>
     * 1= taken<br>
     * 2= taken by bad parker<br>
     * 3= taken by passholder<br>
     * 4= taken by reserving car<br>
     * 12= taken as bad parker's 2nd place<br>
     * 14= reserved by a car/not yet arrived
     */
    public int getLocationState(Location loc) {
        if (stateMap.get(loc.toString()) == null) {
            return 0;
        }
        return stateMap.get(loc.toString());
    }

    /**
     *
     * @param loc location to be set
     * @param car instance of a child of Car, determines the state to be set
     */
    public void setCarAt(Location loc, Car car) {
        int state = 1; //default
        if (car instanceof BadParkerCar) {
            state = 2;
        }
        else if (car instanceof AdHocCar) {
            state = 1;
        }
        else if (car instanceof Passholders) {
            state = 3;
        }
        else if (car instanceof ReservingCar) {
            state = 14; //not yet parked
        }
        setLocationState(loc, state);
        carMap.put(loc.toString(), car);
    }

    /**
     *
     * @param loc the location from which you wish to retrieve the object
     * @return car object
     */
    public Car getCarAt(Location loc) {
        return carMap.get(loc.toString());
    }

    /**
     *
     * @param loc location to set to empty
     */
    public void removeCarAt(Location loc) {
        stateMap.remove(loc.toString());
        carMap.remove(loc.toString());
    }

    /**
     *
     * @return first leaving Car
     */
    public Car getFirstLeavingCar() {
        for (int floor = 0; floor < model.getNumFloors(); floor++) {
            for (int row = 0; row < model.getNumRows(); row++) {
                for (int place = 0; place < model.getNumPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = getCarAt(location);
                    if (car != null && car.getMinutesLeft() <= 0 && !car.getIsPaying()) {
                        return car;
                    }
                }
            }
        }
        return null;
    }
}
