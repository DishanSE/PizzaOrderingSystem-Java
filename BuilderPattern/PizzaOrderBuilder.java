package BuilderPattern;

import java.util.UUID;

import Models.Pizza;
import Models.User;
import OrderManagement.Order;

public class PizzaOrderBuilder {
    private Order order;

    public PizzaOrderBuilder(User user, boolean isDelivery, String deliveryAddress) {
        this.order = new Order(UUID.randomUUID().toString().substring(0,8).toUpperCase(), user, isDelivery, deliveryAddress);
    }

    public PizzaOrderBuilder addPizza(Pizza pizza) {
        order.addPizza(pizza);
        return this;
    }

    public Order build() {
        return order;
    }
}
