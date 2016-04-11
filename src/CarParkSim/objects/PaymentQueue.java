package CarParkSim.objects;

/**
 *@author Nienke's boys
 *
 *
 * BENODIGD:
 * -  UURTARIEF [check]
 * - RESERVEERSTARTTARIEF [check]
 * - TIJD DIE EEN AUTO GEPARKEERD HEEFT
 * - TOTALE REVENUE VAN DE DAG TOT NU TOE
 * - REVENUE VAN DE AUTO'S DIE OP DIT MOMENT NOG IN DE GARAGE STAAN (DUS EX PASSHOLDERS)
 * -
 * -
 *
 */

public class PaymentQueue extends CarQueue {

    private double quarterRate = 0.5; // default number. Method ChangeHourlyRate to change this number.
    private double revenueToday = 0;
    private double reservationStartAmount = 3;

    public PaymentQueue(){
    }

    // get rate per hour
    public double getQuarterRate(){
        return quarterRate;
    }

    // get the starting payment for reservations
    public double getReservationStartAmount(){
        return reservationStartAmount;
    }

    // change the Start payment for reservations
    public void setReservationStart(double StartAmountReservation){
        reservationStartAmount = StartAmountReservation;
    }

    // change the rate per quarter
    public void setQuarterRate(double RatePerQuarter) {
        quarterRate = RatePerQuarter;
    }


    // calculates the amount of money that has to be paid to the Parking Garage
    public double PaymentAmount(Car car){
        double moneyPayable;
        int quarterAmount = (int) Math.ceil(car.getParkedTime() / 15);


        if(car instanceof ReservingCar){
            moneyPayable = reservationStartAmount + (quarterAmount  * quarterRate);
            revenueToday+= moneyPayable;
            return moneyPayable;

        } else{

            moneyPayable= quarterAmount * quarterRate;
            revenueToday+= moneyPayable;
            return moneyPayable;
        }

    }

    // returns the total revenue of today until now
    public double getRevenueToday(){
        return revenueToday;
    }
    
    public Car remove(){
        
    }

}


