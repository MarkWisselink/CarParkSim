package CarParkSim.objects;

/**
 *
 * @author Nienke's boys
 */
public class ReservingCar extends Car {

    private boolean arrived = false;
    private int minutesTillArrived = 0;

    public ReservingCar() {

    }

    public int getMinutesTillArrived() {
        return minutesTillArrived;
    }

    public void getMinutesTillArrived(int min) {
        if (min >= 0) {
            minutesTillArrived = min;
        }
    }

    /**
     * action that happens every minute (synced with model ticks)
     */
    @Override
    public void tick() {
        minutesLeft--;
        minutesParked++;
    }
}
