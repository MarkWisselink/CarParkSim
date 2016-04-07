package CarParkSim.logic;

import CarParkSim.objects.*;
import java.util.Random;

/**
 *
 * @author Nienke's boys
 *
 */
public class Model extends AbstractModel implements Runnable {

    private int floors;
    private int rows;
    private int places;
    private boolean run = false;

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private LocationGrid grid = new LocationGrid(this);

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;
    private int ticks = 10000;

    private int weekDayArrivals; // average number of arriving cars per hour on a weekday
    private int weekendArrivals; // average number of arriving cars per hour in the weekend
    private int nightReductionRate; // average number of arriving cars per hour / nightReductionRate = avg num/hour at night

    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute

    /**
     * no param -> uses default values
     */
    public Model() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new PaymentQueue();
        exitCarQueue = new CarQueue();

        this.floors = 3;
        this.rows = 6;
        this.places = 30;

        this.weekDayArrivals = 80;
        this.weekendArrivals = 50;
        this.nightReductionRate = 3;

        this.enterSpeed = 3;
        this.paymentSpeed = 10;
        this.exitSpeed = 9;
    }

    /**
     * @param floors the number of floors
     * @param rows the number of rows
     * @param places the number of places
     * @param weekDayArrivals average number of cars/hour during weekdays
     * @param weekendArrivals average number of cars/hour during weekends
     * @param nightReductionRate (average number of cars/hour) / nightReductionRate = avg cars/hour at night
     * @param enterSpeed number of minutes it takes for any car to go from front
     * of enterqueue to parking
     * @param paymentSpeed number of minutes it takes for any car to go from
     * front of paymentqueue to exitqueue
     * @param exitSpeed number of minutes it takes for any car to go from front
     * of exitqueue to going away
     */
    public Model(int floors, int rows, int places, int weekDayArrivals, int weekendArrivals, int nightReductionRate, int enterSpeed, int paymentSpeed, int exitSpeed) {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new PaymentQueue();
        exitCarQueue = new CarQueue();

        this.floors = floors;
        this.rows = rows;
        this.places = places;

        this.weekDayArrivals = weekDayArrivals;
        this.weekendArrivals = weekendArrivals;
        this.nightReductionRate = nightReductionRate;

        this.enterSpeed = enterSpeed;
        this.paymentSpeed = paymentSpeed;
        this.exitSpeed = exitSpeed;
    }

    /**
     * speeds up the simulation speed
     */
    public void speedUp() {
        changeSpeed(50, true);
    }

    /**
     * slows down the execution speed
     */
    public void slowDown() {
        changeSpeed(50, false);
    }

    private void changeSpeed(int change, boolean increase) {
        if (!increase) {
            tickPause = tickPause + change;
        }
        else if (change < tickPause) {
            if((tickPause - change) > 1){
                tickPause = tickPause - change;
            }else{
                tickPause = 1;
            }
        }
        System.out.println("new tickpause:" +tickPause);
    }

    /**
     * @return number of floors
     */
    public int getNumFloors() {
        return floors;
    }

    /**
     *
     * @param floors number of floors
     */
    public void setNumFloors(int floors) {
        if (floors > 0) {
            this.floors = floors;
        }
        else {
            //throw exeption
        }
    }

    /**
     *
     * @return number of rows
     */
    public int getNumRows() {
        return rows;
    }

    /**
     *
     * @param rows number of rows (above 0)
     */
    public void setNumRows(int rows) {
        if (rows > 0) {
            this.rows = rows;
        }
        else {
            //throw exeption
        }
    }

    /**
     *
     * @return number of places
     */
    public int getNumPlaces() {
        return places;
    }

    /**
     *
     * @param places number of places (above 0)
     */
    public void setNumPlaces(int places) {
        if (places > 0) {
            this.places = places;
        }
        else {
            //throw exeption
        }
    }

    public int getNumParkingPlaces(){
        return rows * places * floors;
    }
    
    public int getNumCars(String type){
        return 9;
    }
    
    public String getTime(){
        return "";
    }
    
    /**
     *
     * @param location
     * @return int state of the location
     */
    public int getLocInfo(Location location) {
        return grid.getLocationState(location);
    }

    /**
     * start a threaded process, if not already running
     */
    public void start() {
        if (!run) {
            new Thread(this).start();
        }
    }

    /**
     * stop all actions
     */
    public void stop() {
        run = false;
    }

    /**
     *
     * @param ticks the amount of minutes the simulation will run for
     */
    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    private Car createNewCar() {
        return new AdHocCar();
    }

    private void putCarInPark(Car car) {
        Random random = new Random();
        Location freeLocation = grid.getFirstFreeLocation();
        if (freeLocation == null) {
            return;
        }
        if (car instanceof BadParkerCar) {
            Location secondLoc = grid.getSecondLocation();
            if (secondLoc != null) {
                grid.setLocationState(secondLoc, 2);
            }
        }
        else if (car instanceof AdHocCar) {

        }
        else if (car instanceof Passholders) {

        }
        else if (car instanceof ReservingCar) {

        }

        grid.setCarAt(freeLocation, car);
        car.setLocation(freeLocation);
        int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
        car.setMinutesLeft(stayMinutes);

    }

    /**
     *
     */
    public void tickCars() {
        for (int floor = 0; floor < getNumFloors(); floor++) {
            for (int row = 0; row < getNumRows(); row++) {
                for (int place = 0; place < getNumPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = grid.getCarAt(location);
                    if (car != null) {
                        car.tick();
                    }
                }
            }
        }
    }

    /**
     * running until stopped or until ticks run out never call outside a thread!
     */
    @Override
    public void run() {
        run = true;
        while (run && (ticks >= 0)) {
            try {
                ticks -= 1;
                doTick();
                Thread.sleep(tickPause);
                notifyViews();
            }
            catch (Exception e) {
            }
        }
    }

    /**
     * actions to perform while running
     */
    private void doTick() {
        // Advance the time by one minute.
        minute++;
        while (minute > 59) {
            minute = 0;
            hour++;
            System.out.println("hour:" + hour);
        }
        while (hour > 23) {
            hour -= 24;
            day++;
            System.out.println("day:" + day);
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour;
        if (day < 5) {
            averageNumberOfCarsPerHour = weekDayArrivals;
        }
        else {
            averageNumberOfCarsPerHour = weekendArrivals;
        }
        if (hour > 20 || hour < 7) {
            averageNumberOfCarsPerHour = (int) Math.ceil(averageNumberOfCarsPerHour / nightReductionRate);
        }

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int) Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            Car car = createNewCar();
            entranceCarQueue.addCar(car);
        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }

            putCarInPark(car);
        }

        // Perform car park tick.
        tickCars();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = grid.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            if (car instanceof Passholders) {
                exitCarQueue.addCar(car);
            }
            else {
                paymentCarQueue.addCar(car);
                car.setIsPaying(true);
            }
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // TODO Handle payment.
            grid.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
        }

        // Let cars leave.
        for (int i = 0; i < exitSpeed; i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Bye!
        }

    }
}
