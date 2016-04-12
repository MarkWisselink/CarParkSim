package CarParkSim.logic;

import CarParkSim.objects.*;
import java.util.*;
import java.lang.reflect.*;

/**
 *
 * @author Nienke's boys
 *
 */
public class Model extends AbstractModel implements Runnable {

    private boolean run = false;

    private PaymentHelper helper = new PaymentHelper();
    private PaymentQueue paymentCarQueue = new PaymentQueue(helper);
    private CarQueue entranceCarQueue = new CarQueue();
    private CarQueue exitCarQueue = new CarQueue();
    private LocationGrid grid = new LocationGrid(this);
    private HashMap<String, Integer> stats = new HashMap<>();

    private int day = 0;
    private int hour = 0;
    private int minute = 0;
    private boolean weekend;
    private boolean night;

    private int floors = 3;
    private int rows = 6;
    private int places = 30;

    private int tickPause = 100;

    private int weekDayArrivals = 80; // average number of arriving cars per hour on a weekday
    private int weekendArrivals = 100; // average number of arriving cars per hour in the weekend
    private double nightReductionRate = 3; // average number of arriving cars per hour / nightReductionRate = avg num/hour at night

    private int paymentSpeed = 6; // number of cars that can pay per minute
    private int numberOfGates = 2; // number of gates
    private int gateSpeed = 2; // speed of 1 gate
    private double enterSpeedMult = 1; // number of cars that can enter per minute
    private double exitSpeedMult = 1; // number of cars that can leave per minute

    //the following probabilities must be below 1; if none of these cars are spawned, a default car will spawn
    // The probability that a passholder car will spawn.
    private double PASSHOLDER_CAR_CREATION_PROBABILITY = 0.3;
    // The probability that a reserving car will spawn.
    private double RESERVING_CAR_CREATION_PROBABILITY = 0.2;
    // The probability that a bad parker will spawn.
    private double BADPARKER_CAR_CREATION_PROBABILITY = 0.01;

    /**
     * model for the Car Park Simulator
     */
    public Model() {

    }

    /**
     * changes settings based on the name of a field
     *
     * @param settingName string of the fieldname (some fields are forbidden)
     * @param value a double or int value
     */
    public void changeSetting(String settingName, double value) {
        //forbidden names
        if (settingName.equalsIgnoreCase("helper")) {
            return;
        }
        else if (settingName.equalsIgnoreCase("entranceCarQueue")) {
            return;
        }
        else if (settingName.equalsIgnoreCase("paymentCarQueue")) {
            return;
        }
        else if (settingName.equalsIgnoreCase("exitCarQueue")) {
            return;
        }
        else if (settingName.equalsIgnoreCase("grid")) {
            return;
        }
        else if (settingName.equalsIgnoreCase("stats")) {
            return;
        }
        Class<?> c = this.getClass();
        String type = "";
        try {
            Field f = c.getDeclaredField(settingName);
            Type t = f.getGenericType();
            type = t.toString();

            if ((type.equals("int"))) {
                f.set(this, (int) value);
            }
            else if (type.equals("double")) {
                f.set(this, (double) value);
            }
        }
        catch (NoSuchFieldException x) {
            System.out.println("no such field");
        }
        catch (IllegalAccessException x) {
            System.out.println("illegal access");
        }
        catch (ClassCastException x) {
            System.out.println("class cast exception; name:" + type);

        }
    }

    /**
     * speeds up the simulation speed
     */
    public void speedUp() {
        changeSpeed(30, true);
    }

    /**
     * slows down the execution speed
     */
    public void slowDown() {
        changeSpeed(30, false);
    }

    private void changeSpeed(int change, boolean increase) {
        if (!increase) {
            tickPause = tickPause + change;
        }
        else if ((tickPause - change) > 1) {
            tickPause = tickPause - change;
        }
        else {
            tickPause = 5;
        }
    }

    /**
     *
     * @return number of gates
     */
    public int getNumberOfGates() {
        return numberOfGates;
    }

    /**
     *
     * @param numberOfGates number of gates
     */
    public void setNumberOfGates(int numberOfGates) {
        if (numberOfGates > 0) {
            this.numberOfGates = numberOfGates;
        }
        else {
            //throw exception
        }
    }

    /**
     * @return number of floors
     */
    public int getNumFloors() {
        return floors;
    }

    /**
     *
     * @param floors number of floors (above 0)
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
     * @param query either "free" to find the amount of free spots, or anything
     * else to find the full spots
     * @return int value of the number of free/taken spots
     */
    public int getNumParkingPlaces(String query) {
        if (query.equals("free")) {
            return (((rows * places * floors) - getNumCars("parked")) - getStat("reserved"));
        }
        return rows * places * floors;
    }

    /**
     *
     * @return int value of the number of taken spots
     */
    public int getNumParkingPlaces() {
        return getNumParkingPlaces("taken");
    }

    /**
     * deprecated. only used for backwards compatibility using getStat is better
     *
     * @param type enterq, parked, payq, or exitq
     * @return number of cars, in a given state
     */
    public int getNumCars(String type) {
        if (type.equalsIgnoreCase("enterq")) {
            return getStat("entranceQ");
        }
        else if (type.equalsIgnoreCase("parked")) {
            return getStat("parkedCurrent");
        }
        else if (type.equalsIgnoreCase("payq")) {
            return getStat("paymentQ");
        }
        else if (type.equalsIgnoreCase("exitq")) {
            return getStat("exitQ");
        }
        else {
            return 0;
        }
    }

    /**
     * @param key string name of the stat
     * @return int value corresponding to the stat name<br>
     * returns 0 if stat wasn't found
     */
    public int getStat(String key) {
        if (stats.get(key) != null) {
            return stats.get(key);
        }
        return 0;
    }

    /**
     * @param key string name of the stat
     * @param rev set to true if you want revenue related stats<br>
     * false if you want to retrieve a double variant of a stat
     * @return double value corresponding to the stat name<br>
     * returns 0 if stat wasn't found
     */
    public double getStat(String key, boolean rev) {
        if (rev) {
            if (key.equals("revenueTotal")) {
                return helper.getRevenueTotal();
            }
            else if (key.equals("revenueToday")) {
                return helper.getRevenueToday();
            }
            else {
                return 0;
            }
        }
        else {
            return (double) getStat(key);
        }
    }

    /**
     * sets a stat with name: key; value: val<br>
     * updates if a duplicate name is found<br>
     *
     * @param key string name; preferably logical, and unused
     * @param val int value
     */
    public void setStat(String key, int val) {
        if (val >= 0) {
            stats.put(key, val);
        }
    }

    /**
     *
     * @return a string representation of the time<br>
     * "NameOfDay. hh:mm" with leading zeros
     */
    public String getTime() {
        String daystr;
        String hourstr;
        String minstr;
        switch (day) {
            case 0:
                daystr = "Monday";
                break;
            case 1:
                daystr = "Tuesday";
                break;
            case 2:
                daystr = "Wednesday";
                break;
            case 3:
                daystr = "Thursday";
                break;
            case 4:
                daystr = "Friday";
                break;
            case 5:
                daystr = "Saturday";
                break;
            case 6:
                daystr = "Sunday";
                break;
            default:
                daystr = "Unknown";
        }

        if (hour < 10) {
            if (hour == 0) {
                hourstr = "00";
            }
            else {
                hourstr = "0" + ((Integer) hour).toString();
            }
        }
        else {
            hourstr = "" + hour;
        }

        if (minute < 10) {
            if (minute == 0) {
                minstr = "00";
            }
            else {
                minstr = "0" + ((Integer) minute).toString();
            }
        }
        else {
            minstr = "" + minute;
        }

        return daystr + ". " + hourstr + ":" + minstr;
    }

    private void counterIncrease(String key) {
        this.counterIncrease(key, 1);
    }

    private void counterIncrease(String key, int val) {
        if (stats.get(key) == null) {
            stats.put(key, val);
        }
        else {
            stats.put(key, (stats.get(key) + val));
        }
    }

    private void counterDecrease(String key) {
        this.counterDecrease(key, 1);
    }

    private void counterDecrease(String key, int val) {
        if (stats.get(key) == null) {
            stats.put(key, 0);
        }
        else if ((stats.get(key) - val) >= 0) {
            stats.put(key, (stats.get(key) - val));
        }
    }

    /**
     * @param location
     * @return int state of the location<br>
     * 0= empty<br>
     * 1= taken<br>
     * 2= taken by bad parker<br>
     * 3= taken by passholder<br>
     * 4= taken by reserving car<br>
     * 12= taken as bad parker's 2nd place<br>
     * 14= reserved by a car/not yet arrived
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

    private Car createNewCar() {
        Random random = new Random();
        if (random.nextDouble() <= PASSHOLDER_CAR_CREATION_PROBABILITY) {
            counterIncrease("totalPassholders");
            counterIncrease("currentPassholders");
            return new Passholders();
        }
        else if (random.nextDouble() <= BADPARKER_CAR_CREATION_PROBABILITY) {
            counterIncrease("totalBadParkerCar");
            counterIncrease("currentBadParkerCar");
            return new BadParkerCar();
        }
        else if (random.nextDouble() <= RESERVING_CAR_CREATION_PROBABILITY) {
            Location freeLocation = grid.getFirstFreeLocation();
            if (freeLocation == null) {//no free spot => no reservation allowed
                counterIncrease("totalAdHocCar");
                counterIncrease("currentAdHocCar");
                return new AdHocCar();
            }
            counterIncrease("totalReservingCar");
            counterIncrease("currentReservingCar");
            ReservingCar car = new ReservingCar();
            car.setLocation(freeLocation);
            car.setMinutesLeft(generateStayMinutes());
            int minutesTillArrived = (int) (random.nextFloat() * 60 * 3);
            car.setMinutesTillArrived(minutesTillArrived);
            grid.setCarAt(freeLocation, car);
            
            //process payment
            helper.paymentAmount(car, false);

            return car;
        }
        else {
            counterIncrease("totalAdHocCar");
            counterIncrease("currentAdHocCar");
            return new AdHocCar();
        }
    }

    private boolean putCarInPark(Car car) {
        Location freeLocation = grid.getFirstFreeLocation();
        if (freeLocation == null) {
            return false;
        }
        if (car instanceof BadParkerCar) {
            Location secondLoc = grid.getFirstFreeLocation(true);
            if (secondLoc != null) {
                ((BadParkerCar) car).setSecondLocation(secondLoc);
                grid.setLocationState(secondLoc, 12);
            }
        }
        else if (car instanceof ReservingCar) {
            grid.setLocationState(car.getLocation(), 4);
            return true;
        }
        /*else if (car instanceof AdHocCar) {

        } else if (car instanceof Passholders) {

        } */

        grid.setCarAt(freeLocation, car);
        car.setLocation(freeLocation);
        int stayMinutes = generateStayMinutes();
        car.setMinutesLeft(stayMinutes);
        return true;
    }

    private int generateStayMinutes() {
        Random random = new Random();
        int stayMinutes;
        if (night) {
            stayMinutes = 8 * 60 + (int) (random.nextFloat() * 2 * 60);
        }
        else {
            stayMinutes = 15 + (int) (random.nextFloat() * 10 * 60);
        }
        if (weekend) {
            stayMinutes += (int) (random.nextFloat() * 1.5 * 60);
        }
        return stayMinutes;
    }

    private HashMap<String, Integer> processGateState() {
        HashMap<String, Integer> ret = new HashMap<>();
        int enterQSize = entranceCarQueue.size();
        int exitQSize = exitCarQueue.size();
        int enterGates = 0;
        int exitGates = 0;
        for (int i = 0; i < numberOfGates; i++) {
            if (0 < enterQSize && enterQSize >= exitQSize) {// enter queue length is between 0 and the exit queue length
                enterGates++;
                enterQSize -= gateSpeed * exitSpeedMult;
            }
            else if (0 < exitQSize && exitQSize >= enterQSize) { // exit queue length is between 0 and the enter queue length
                exitGates++;
                exitQSize -= gateSpeed * enterSpeedMult;
            }
        }
        ret.put("enter", enterGates);
        ret.put("exit", exitGates);
        return ret;
    }

    private void tickCars() {
        helper.resetRevenueToday();
        for (int floor = 0; floor < getNumFloors(); floor++) {
            for (int row = 0; row < getNumRows(); row++) {
                for (int place = 0; place < getNumPlaces(); place++) {
                    Location location = new Location(floor, row, place);
                    Car car = grid.getCarAt(location);
                    if (car != null) {
                        car.tick();
                        helper.paymentAmount(car, true);
                        if (car instanceof ReservingCar) {
                            ReservingCar reservingCar = (ReservingCar) car;
                            if (reservingCar.getMinutesTillArrived() <= 0) {
                                if (grid.getLocationState(location) == 14 && !reservingCar.getArrived()) {
                                    reservingCar.setArrived(true);
                                    entranceCarQueue.addCar(reservingCar);
                                    this.counterIncrease("entranceQ");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * running until stopped<br>
     * never call outside a thread!
     */
    @Override
    public void run() {
        run = true;
        while (run) {
            try {
                doTick();
                Thread.sleep(tickPause);
                notifyViews();
            }
            catch (Exception e) {
            }
        }
        this.stop();
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
        int averageNumberOfCarsPerHour;
        if (day < 5) {
            averageNumberOfCarsPerHour = weekDayArrivals;
            weekend = false;
        }
        else {
            averageNumberOfCarsPerHour = weekendArrivals;
            weekend = true;
        }
        if (hour > 20 || hour < 7) {
            night = true;
            averageNumberOfCarsPerHour = (int) Math.ceil(averageNumberOfCarsPerHour / nightReductionRate);
        }
        else {
            night = false;
        }
        this.counterIncrease("entranceQ", 0);

        /*double drCurve = 4*(1 + (Math.pow(
                1.29,
                ((-0.9) * (getNumCars("enterq"))) + (4.5315 * Math.log(averageNumberOfCarsPerHour)-2.6729)
        )));
        //doesn't work as expected, because Java sucks at math :/
         */
        double drCurve = averageNumberOfCarsPerHour - (10 * getNumCars("enterq"));
        if (drCurve < 1) {
            drCurve = 1;
        }
        // Calculate the number of cars that arrive this minute.
        double standardDeviation = drCurve * 0.1;
        double numberOfCarsPerHour = drCurve + (random.nextGaussian() * standardDeviation);
        //int numberOfCarsPerMinute = (int) Math.round(numberOfCarsPerHour / 60);
        int numberOfCarsPerMinute = 0;
        while (numberOfCarsPerHour > 60) {
            numberOfCarsPerMinute++;
            numberOfCarsPerHour -= 60;
        }
        if (random.nextDouble() < (numberOfCarsPerHour / 60)) {
            numberOfCarsPerMinute++;
        }

        // Add the cars to the back of the queue.
        for (int i = 0; i < numberOfCarsPerMinute; i++) {
            Car car = createNewCar();
            if (!(car instanceof ReservingCar)) {
                entranceCarQueue.addCar(car);
                this.counterIncrease("entranceQ");
            }
        }

        // Perform car park tick.
        tickCars();

        // Add leaving cars to the exit queue.
        while (true) {
            Car car = grid.getFirstLeavingCar();
            if (car == null) {
                break;
            }
            this.counterDecrease("parkedCurrent");
            if (car instanceof Passholders) {
                exitCarQueue.addCar(car);
                this.counterIncrease("exitQ");
                car.setIsPaying(true);
                grid.removeCarAt(car.getLocation());
            }
            else {
                paymentCarQueue.addCar(car);
                this.counterIncrease("paymentQ");
                car.setIsPaying(true);
            }
        }

        // Let cars pay.
        for (int i = 0; i < paymentSpeed; i++) {
            Car car = paymentCarQueue.removeCar();
            if (car == null) {
                break;
            }
            this.counterDecrease("paymentQ");
            if (car instanceof BadParkerCar) {
                grid.setLocationState(((BadParkerCar) car).getSecondLocation(), 0);
                //grid.removeCarAt(((BadParkerCar) car).getSecondLocation());
            }
            grid.removeCarAt(car.getLocation());
            exitCarQueue.addCar(car);
            this.counterIncrease("exitQ");
        }

        //process car queues
        //
        //get gate states
        HashMap<String, Integer> gates = new HashMap<>();
        gates = processGateState();

        int entranceGates = gates.get("enter");
        int exitGates = gates.get("exit");

        // Remove car from the front of the queue and assign to a parking space.
        for (int i = 0; i < (int) (gateSpeed * enterSpeedMult * entranceGates); i++) {
            Car car = entranceCarQueue.removeCar();
            if (car == null) {
                break;
            }
            if (putCarInPark(car)) {
                this.counterDecrease("entranceQ");
                this.counterIncrease("parkedCurrent");
                this.counterIncrease("parkedTotal");
            }
        }

        // Let cars leave.
        for (int i = 0; i < (int) (gateSpeed * exitSpeedMult * exitGates); i++) {
            Car car = exitCarQueue.removeCar();
            if (car == null) {
                break;
            }
            if (car instanceof AdHocCar) {
                this.counterDecrease("currentAdHocCar");
            }
            else if (car instanceof BadParkerCar) {
                this.counterDecrease("currentBadParkerCar");
            }
            else if (car instanceof Passholders) {
                this.counterDecrease("currentPassholders");
            }
            else if (car instanceof ReservingCar) {
                this.counterDecrease("currentReservingCar");
            }

            this.counterDecrease("exitQ");
            // Bye!
        }

    }
}
