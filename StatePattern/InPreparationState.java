package StatePattern;

import OrderManagement.Order;
import OrderManagement.OrderStatus;

public class InPreparationState extends OrderState {
    public InPreparationState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.IN_PREPARATION;
    }

    @Override 
    public void prepare() {
        // Do preparation work
        System.out.println("Preparing order " + order.getOrderId());
    }

    @Override
    public void deliver() {
        order.setState(new OutForDeliveryState(order));
        System.out.println("Order " + order.getOrderId() + " is out for delivery");
    }
}
