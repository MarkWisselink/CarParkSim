package CarParkSim.objects;

/**
 *@author Nienke's boys
 *
 *
 * BENODIGD:
 * -  UURTARIEF [check]
 * - TIJD DIE EEN AUTO GEPARKEERD HEEFT
 * - TOTALE REVENUE VAN DE DAG TOT NU TOE
 * - REVENUE VAN DE AUTO'S DIE OP DIT MOMENT NOG IN DE GARAGE STAAN (DUS EX PASSHOLDERS)
 * - PASSHOLDER JA/NEE -> DOORSTUREN NAAR EXIT
 * -
 *
 */

public class PaymentQueue extends CarQueue {

    private double hourlyRate = 1.50; // default number. Method ChangeHourlyRate to change this number.
    private double revenueToday = 0;
   // private double RatePerhour;


    // get rate per hour
    public double getHourlyRate(){
        return hourlyRate;
    }

    public double ChangeHourlyRate(double RatePerHour){
        hourlyRate = RatePerHour;
        return hourlyRate;
    }

    // calculates the amount of money that has to be paid to the Parking Garage
    public double PaymentAmount(Car car){
        double moneyPayable = 0;

        if(car instanceof Passholders){
            return moneyPayable;
        } else{


           // moneyPayable = hourlyRate * timeParked;
            revenueToday+= moneyPayable;
            return moneyPayable;
        }

    }

}


