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
        stateMap = new HashMap<String, Integer>();
        carMap = new HashMap<String, Car>();
    }

    /**
     *
     * @return Location object for a free location
     */
    public Location getFirstFreeLocation() {
        for (int floor = 0; floor < model.getNumFloors(); floor++) {
            for (int row = 0; row < model.getNumRows(); row++) {
                for (int place = 0; place < model.getNumPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    if (getLocationState(location) == 0) {
                        return location;
                    }
                }
            }
        }
        return null;
    }

    public Location getSecondLocation() {
        boolean first = true;
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
     * @param loc Location
     * @param state state to set (0= empty; 1= taken)
     */
    public void setLocationState(Location loc, int state) {
        stateMap.put(loc.toString(), state);
    }

    /**
     *
     * @param loc Location
     * @return int value of the state (0= empty; 1= taken)
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
        //should depend on car type/info
        setLocationState(loc, 1);
        carMap.put(loc.toString(), car);
    }

    private Car getCarAt(Location loc) {
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
