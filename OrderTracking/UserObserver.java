package OrderTracking;

import Models.User;
import OrderManagement.Order;

public class UserObserver implements Observer{
    private User user;
    
    public UserObserver(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void update(Order order) {
        System.out.println("Order " + order.getOrderId() + " status updated to " + order.getStatus());
    }
}
