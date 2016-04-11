package CarParkSim.objects;

import old.*;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Nienke's boys
 */
public class CarQueue {
    protected  Queue<Car> queue = new LinkedList<>();

    /**
     *
     * @param car instance of Car to be added to queue
     * @return fail or succes
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

}
