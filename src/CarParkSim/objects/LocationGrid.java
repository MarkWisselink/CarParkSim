package CarParkSim.objects;

import java.util.*;
import CarParkSim.logic.*;

public class LocationGrid {

    private HashMap<String, Integer> locationMap;

    public LocationGrid() {
        locationMap = new HashMap<String, Integer>();
    }

    public Location getFirstFreeLocation(Model model) {
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

    public void setLocationState(Location loc, int state) {
        locationMap.put(loc.toString(), state);
    }

    public int getLocationState(Location loc) {
        if (locationMap.get(loc.toString()) == null) {
            return 0;
        }
        return locationMap.get(loc.toString());
    }

    public void setCarAt(Location loc, Car car) {
        //should depend on car type/info
        setLocationState(loc, 1);
    }

    public void removeCarAt(Location loc) {
        setLocationState(loc, 0);
    }

    public Car getFirstLeavingCar() {
        return null;
    }
}
