package CarParkSim.logic;

import CarParkSim.objects.*;
import java.util.Random;

public class Model extends AbstractModel implements Runnable {

    private int floors;
    private int rows;
    private int places;
    private boolean run = false;

    private CarQueue entranceCarQueue;
    private CarQueue paymentCarQueue;
    private CarQueue exitCarQueue;
    private LocationGrid grid = new LocationGrid();

    private int day = 0;
    private int hour = 0;
    private int minute = 0;

    private int tickPause = 100;
    private int ticks = 10000;

    int weekDayArrivals; // average number of arriving cars per hour
    int weekendArrivals; // average number of arriving cars per hour

    int enterSpeed; // number of cars that can enter per minute
    int paymentSpeed; // number of cars that can pay per minute
    int exitSpeed; // number of cars that can leave per minute

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

    public int getNumFloors() {
        return floors;
    }

    public void setNumFloors(int floors) {
        this.floors = floors;
    }

    public int getNumRows() {
        return rows;
    }

    public void setNumRows(int rows) {
        this.rows = rows;
    }

    public int getNumPlaces() {
        return places;
    }

    public void setNumPlaces(int places) {
        this.places = places;
    }

    public int getLocInfo(Location location) {
        return grid.getLocationState(location);
    }

    public void start() {
        if (!run) {
            new Thread(this).start();
        }
    }

    public void stop() {
        run = false;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }

    @Override
    public void run() {
        run = true;
        while (run && ticks >= 0) {
            try {
                doTick();
                Thread.sleep(tickPause);
                notifyViews();
            }
            catch (Exception e) {
            }
        }
    }

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
            Location freeLocation = grid.getFirstFreeLocation(this);
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
