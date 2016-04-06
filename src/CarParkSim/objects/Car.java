package CarParkSim.objects;

import old.*;

/**
 *
 * @author Nienke's boys
 */
public abstract class Car {

    private Location location;
    private int minutesLeft;
    private boolean isPaying;

    /**
     * Constructor for objects of class Car
     */
    public Car() {

    }

    /**
     *
     * @return returns location object
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location location object of the car
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return int of the minutes left before leaving
     */
    public int getMinutesLeft() {
        return minutesLeft;
    }

    /**
     *
     * @param minutesLeft int of the minutes left before leaving
     */
    public void setMinutesLeft(int minutesLeft) {
        this.minutesLeft = minutesLeft;
    }
    
    /**
     *
     * @return boolean if car is currently paying
     */
    public boolean getIsPaying() {
        return isPaying;
    }

    /**
     *
     * @param isPaying boolean if car is currently paying
     */
    public void setIsPaying(boolean isPaying) {
        this.isPaying = isPaying;
    }

    /**
     * action that happens every minute (synced with model ticks)
     */
    public void tick() {
        minutesLeft--;
    }

}