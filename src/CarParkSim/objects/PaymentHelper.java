package CarParkSim.objects;

/**
 * @author Nienke's boys on 11-4-2016.
 */
public class PaymentHelper {

    private double quarterRate = 0.5; // default number. Method ChangeHourlyRate to change this number.
    private double revenueToday;
    private double reservationStartAmount = 3;
    private double revenueTotal;

    /**
     *
     * @return double value of the current quarter rate
     */
    public double getQuarterRate() {
        return quarterRate;
    }

    /**
     *
     * @return get the starting payment for reservations
     */
    public double getReservationStartAmount() {
        return reservationStartAmount;
    }

    /**
     * change the Start payment for reservations
     *
     * @param StartAmountReservation new value
     */
    public void setReservationStart(double StartAmountReservation) {
        reservationStartAmount = StartAmountReservation;
    }

    /**
     * change the rate per quarter
     *
     * @param RatePerQuarter new rate
     */
    public void setQuarterRate(double RatePerQuarter) {
        quarterRate = RatePerQuarter;
    }

    //
    /**
     * calculates the amount of money that has to be paid to the Parking Garage
     *
     * @param car the car object which needs to be parsed
     * @param expected if it's to calculate the expected revenue
     * @return double value, containing the calculated price; this number is
     * automatically added to the total and daily revenue counters
     */
    public double paymentAmount(Car car, boolean expected) {
        double moneyPayable;
        int minutes;
        int quarterAmount;

        if (car instanceof ReservingCar) {
            minutes = ((ReservingCar) car).getPayedTime();
            quarterAmount = (int) Math.ceil(minutes / 15);
            moneyPayable = reservationStartAmount + (quarterAmount * quarterRate);
            if (!expected) {
                revenueTotal += moneyPayable;
                revenueToday += moneyPayable;
            }
            return moneyPayable;

        }
        else {
            minutes = car.getMinutesLeft();
            if (minutes < 0) {
                minutes = car.getParkedTime();
            }
            else {
                minutes += car.getParkedTime();
            }
            quarterAmount = (int) Math.ceil(minutes / 15);

            moneyPayable = quarterAmount * quarterRate;
            if (!expected) {
                revenueTotal += moneyPayable;
                revenueToday += moneyPayable;
            }
            return moneyPayable;
        }

    }

    /**
     * call this method at the start of every day
     */
    public void resetRevenueToday() {
        revenueToday = 0;
    }

    /**
     *
     * @return the total revenue of today until now
     */
    public double getRevenueToday() {
        return revenueToday;
    }

    /**
     *
     * @return the total revenue. ever
     */
    public double getRevenueTotal() {
        return revenueTotal;
    }

}
