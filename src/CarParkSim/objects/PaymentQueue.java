package CarParkSim.objects;

/**
 * @author Nienke's boys
*/

public class PaymentQueue extends CarQueue {
    private PaymentHelper helper;


    public PaymentQueue(PaymentHelper helper) {
        this.helper = helper;
    }


    private void procesCarPayment(Car car) {
        helper.paymentAmount(car);
    }

    @Override
    public Car removeCar() {
        Car car = queue.poll();
        if (car != null) {
            procesCarPayment(car);
        }

        return car;
    }
}
