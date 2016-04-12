package CarParkSim.objects;

/**
 *
 * @author Nienke's boys
 */
public abstract class Car {

    /**
     * location of this car
     */
    protected Location location;

    /**
     * amount of minutes until the car will try leave
     */
    protected int minutesLeft;

    /**
     * the amount of minutes the car has parked (used for determining the ticket
     * price)
     */
    protected int minutesParked = 0;
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
     * @return int of the minutes that the car has been staying
     */
    public int getParkedTime() {
        return minutesParked;
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
        minutesParked++;
    }

}
