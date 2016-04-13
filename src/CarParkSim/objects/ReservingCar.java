package CarParkSim.objects;

/**
 *
 * @author Nienke's boys
 */
public class ReservingCar extends Car {

    private boolean arrived = false;
    private boolean arriving = false;
    private int minutesTillArrived = 0;
    private int payedTime = 0;

    /**
     * constructor for a reserving car, this car type needs more work in the
     * model than a default car
     */
    public ReservingCar() {

    }

    /**
     *
     * @return the amount of minutes until this car will arrive
     */
    public int getMinutesTillArrived() {
        return minutesTillArrived;
    }

    /**
     *
     * @param min the amount of minutes until this car will arrive
     */
    public void setMinutesTillArrived(int min) {
        if (min >= 0) {
            payedTime = (payedTime - minutesTillArrived) + min;
            minutesTillArrived = min;
        }
    }

    /**
     *
     * @param minutesLeft int of the minutes left before leaving
     */
    @Override
    public void setMinutesLeft(int minutesLeft) {
        if (this.minutesLeft == 0) {
            payedTime += minutesLeft;
        } else {
            payedTime = (payedTime - this.minutesLeft) + minutesLeft;
        }
        this.minutesLeft = minutesLeft;
    }

    /**
     *
     * @return the amount of time this car will pay for
     */
    public int getPayedTime() {
        return payedTime;
    }

    /**
     *
     * @param arrived if this car has arrived
     */
    public void setArrived(boolean arrived) {
        this.arrived = arrived;
    }

    /**
     *
     * @return if this car has arrived
     */
    public boolean getArrived() {
        return arrived;
    }

    /**
     *
     * @param arriving if this car is currently arriving/in entranceQ
     */
    public void setArriving(boolean arriving) {
        this.arriving = arriving;
    }

    /**
     *
     * @return if this car is currently arriving/in entranceQ
     */
    public boolean getArriving() {
        return arriving;
    }

    /**
     * action that happens every minute (synced with model ticks)
     */
    @Override
    public void tick() {
        if (!arriving && !arrived) {
            minutesTillArrived--;
        } else {
            minutesLeft--;
            minutesParked++;
        }
    }
}
