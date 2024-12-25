package StatePattern;

import OrderManagement.Order;
import OrderManagement.OrderStatus;

public abstract class OrderState {
     protected Order order;

     public OrderState() {}

     public OrderState(Order order) {
        this.order = order;
     }

     public abstract OrderStatus getStatus();
     public abstract void prepare();
     public abstract void deliver();
}
