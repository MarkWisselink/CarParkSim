package CarParkSim.objects;

/**
 * @author Nienke's boys on 11-4-2016.
 */
public class PaymentHelper {
    private double quarterRate = 0.5; // default number. Method ChangeHourlyRate to change this number.
    private double revenueToday;
    private double reservationStartAmount = 3;
    private double revenueTotal;

    // get rate per hour
    public double getQuarterRate() {
        return quarterRate;
    }



    // get the starting payment for reservations
    public double getReservationStartAmount() {
        return reservationStartAmount;
    }

    // change the Start payment for reservations
    public void setReservationStart(double StartAmountReservation) {
        reservationStartAmount = StartAmountReservation;
    }

    // change the rate per quarter
    public void setQuarterRate(double RatePerQuarter) {
        quarterRate = RatePerQuarter;
    }


    // calculates the amount of money that has to be paid to the Parking Garage
    public double paymentAmount(Car car, boolean expected) {
        double moneyPayable;
        int minutes = car.getMinutesLeft();
        if(minutes < 0){
            minutes = car.getParkedTime();
        }else{
            minutes += car.getParkedTime();
        }
        int quarterAmount = (int) Math.ceil(minutes / 15);

        if (car instanceof ReservingCar) {
            moneyPayable = reservationStartAmount + (quarterAmount * quarterRate);
            if(!expected){
            revenueTotal += moneyPayable;
            revenueToday += moneyPayable;
            }
            return moneyPayable;

        }
        else {

            moneyPayable = quarterAmount * quarterRate;
            if(!expected) {
                revenueTotal += moneyPayable;
                revenueToday += moneyPayable;
            }
            return moneyPayable;
        }

    }




    public void resetRevenueToday(){
        revenueToday = 0;
    }


    // returns the total revenue of today until now
    public double getRevenueToday() {
        return revenueToday;
    }

    public double getRevenueTotal() { return revenueTotal; }

}
