package CarParkSim.logic;

import CarParkSim.objects.*;
import java.util.Random;

/**
 *
 * @author Nienke's boys
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

    private int weekDayArrivals; // average number of arriving cars per hour
    private int weekendArrivals; // average number of arriving cars per hour

    private int enterSpeed; // number of cars that can enter per minute
    private int paymentSpeed; // number of cars that can pay per minute
    private int exitSpeed; // number of cars that can leave per minute

    /**
     * no param -> uses default values
     */
    public Model() {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        this.floors = 3;
        this.rows = 6;
        this.places = 30;

        this.weekDayArrivals = 50;
        this.weekendArrivals = 90;

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
     * @param enterSpeed number of minutes it takes for any car to go from front
     * of enterqueue to parking
     * @param paymentSpeed number of minutes it takes for any car to go from
     * front of paymentqueue to exitqueue
     * @param exitSpeed number of minutes it takes for any car to go from front
     * of exitqueue to going away
     */
    public Model(int floors, int rows, int places, int weekDayArrivals, int weekendArrivals, int enterSpeed, int paymentSpeed, int exitSpeed) {
        entranceCarQueue = new CarQueue();
        paymentCarQueue = new CarQueue();
        exitCarQueue = new CarQueue();

        this.floors = floors;
        this.rows = rows;
        this.places = places;
        this.weekDayArrivals = weekDayArrivals;
        this.weekendArrivals = weekendArrivals;

        this.enterSpeed = enterSpeed;
        this.paymentSpeed = paymentSpeed;
        this.exitSpeed = exitSpeed;
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

    /**
     * running until stopped or until ticks run out
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
        }
        while (hour > 23) {
            hour -= 24;
            day++;
        }
        while (day > 6) {
            day -= 7;
        }

        Random random = new Random();

        // Get the average number of cars that arrive per hour.
        int averageNumberOfCarsPerHour = day < 5
                ? weekDayArrivals // <- if day < 5
                : weekendArrivals; //<- else

        // Calculate the number of cars that arrive this minute.
        double standardDeviation = averageNumberOfCarsPerHour * 0.1;
        double numberOfCarsPerHour = averageNumberOfCarsPerHour + random.nextGaussian() * standardDeviation;
        int numberOfCarsPerMinute = (int) Math.round(numberOfCarsPerHour / 60);

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            Car car = new AdHocCar();
            entranceCarQueue.addCar(car);
        }

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < enterSpeed; i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            // Find a space for this car.
            Location freeLocation = grid.getFirstFreeLocation();
            if (freeLocation != null) {
                grid.setCarAt(freeLocation, car);
                int stayMinutes = (int) (15 + random.nextFloat() * 10 * 60);
                car.setMinutesLeft(stayMinutes);
            }
        }

        // Perform car park tick.
        //simulatorView.tick();
        // Add leaving cars to the exit queue.
        while (true) {
            Car car = grid.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            car.setIsPaying(true);
            paymentCarQueue.addCar(car);
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
