package CarParkSim.objects;

/**
 * @author Nienke's boys
*/

public class PaymentQueue extends CarQueue {
    private PaymentHelper helper;


    public PaymentQueue(PaymentHelper helper) {
        this.helper = helper;
    }


    private void processCarPayment(Car car) {
        helper.paymentAmount(car, false);
    }

    @Override
    public Car removeCar() {
        Car car = queue.poll();
        if (car != null) {
            processCarPayment(car);
        }

        return car;
    }
}
