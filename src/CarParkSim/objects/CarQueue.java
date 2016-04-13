package CarParkSim.objects;

import java.util.*;

/**
 *
 * @author Nienke's boys
 */
public class CarQueue {

    /**
     * a LinkedList object, containing all cars currently in queue
     */
    protected Queue<Car> queue = new LinkedList<>();

    /**
     *
     * @param car instance of Car to be added to queue
     * @return fail or success
     */
    public boolean addCar(Car car) {
        return queue.add(car);
    }

    /**
     *
     * @return instance of Car that is on front of queue
     */
    public Car removeCar() {
        return queue.poll();
    }

    /**
     *
     * @return returns the next car to be removed; without removing it
     */
    public Car getNextCar() {
        return queue.peek();
    }

    /**
     *
     * @param car removes the specified car
     */
    public void remove(Car car) {
        queue.remove(car);
    }

    /**
     *
     * @return the size
     */
    public int size() {
        return queue.size();
    }

}
