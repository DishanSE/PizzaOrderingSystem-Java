package OrderTracking;

import OrderManagement.Order;

public interface Observer {
    void update(Order order);
}
