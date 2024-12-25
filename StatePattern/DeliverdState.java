package StatePattern;

import OrderManagement.Order;
import OrderManagement.OrderStatus;

public class DeliverdState extends OrderState {
    public DeliverdState(Order order) {
        super(order);
    }

    @Override
    public OrderStatus getStatus() {
        return OrderStatus.DELIVERED;
    }
    
    @Override
    public void prepare() {
        throw new IllegalStateException("Order has already been delivered.");
    }

    @Override
    public void deliver() {
        throw new IllegalStateException("Order has already been delivered.");
    }
}
