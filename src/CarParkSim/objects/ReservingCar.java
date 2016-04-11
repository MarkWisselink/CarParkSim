package CarParkSim.objects;

/**
 *
 * @author Nienke's boys
 */
public class ReservingCar extends Car {

    private boolean arrived = false;
    private int minutesTillArrived = 0;
    private int payedTime = 0;

    public ReservingCar() {

    }

    public int getMinutesTillArrived() {
        return minutesTillArrived;
    }

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
        if(this.minutesLeft==0){
            payedTime += minutesLeft;
        }else{
            payedTime = (payedTime - this.minutesLeft) + minutesLeft;
        }
        this.minutesLeft = minutesLeft;
    }

    public int getPayedTime() {
        return payedTime;
    }
    
    public void setArrived(boolean arrived){
        this.arrived = arrived;
    }
    
    public boolean getArrived(){
        return arrived;
    }

    /**
     * action that happens every minute (synced with model ticks)
     */
    @Override
    public void tick() {
        if (!arrived) {
            minutesTillArrived--;
        }
        else {
            minutesLeft--;
            minutesParked++;
        }
    }
}
