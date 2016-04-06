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

    private double QuarterRate = 0.5; // default number. Method ChangeHourlyRate to change this number.
    private double revenueToday = 0;
    private double reservationStartAmount = 3;

    public PaymentQueue(){
    }

    // get rate per hour
    public double getHourlyRate(){
        return hourlyRate;
    }

    // get the starting payment for reservations
    public double getReservationStartAmount(){
        return reservationStartAmount;
    }

    // change the Start payment for reservations
    public double changeHourlyRate(double StartAmountReservation){
        reservationStartAmount = StartAmountReservation;
    }

    // change the rate per hour
    public double changeHourlyRate(double RatePerHour) {
        hourlyRate = RatePerHour;
        return hourlyRate;
    }


    // calculates the amount of money that has to be paid to the Parking Garage
    public double PaymentAmount(Car car){
        double moneyPayable = 0;

        if(car instanceof ReservingCar){
            moneyPayable = reservationStartAmount + (car.getParkedTime() * QuarterRate);
            revenueToday+= moneyPayable;
            return moneyPayable;

        } else{

            moneyPayable= car.getParkedTime() * QuarterRate;
            revenueToday+= moneyPayable;
            return moneyPayable;
        }

    }

    // returns the total revenue of today until now
    public double getRevenueToday(){
        return revenueToday;
    }

}


