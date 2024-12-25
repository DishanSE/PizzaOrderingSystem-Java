package DecoratorPattern;

import java.util.List;

import Models.Pizza;
import Models.Topping;
import OrderManagement.Order;

public class ExtraToppingsOrder extends EnhancedOrder {
    private List<Topping> extraToppings;

    public ExtraToppingsOrder(Order order, List<Topping> extraToppings) {
        super(order);
        this.extraToppings = extraToppings;
    }

    @Override
    public void addFeature() {
        // add extra toppingd to the order
        for (Topping topping : extraToppings) {
            order.addPizza(new Pizza("extraTopping", "Extra Topping", order.getPizzas().get(0).getCrust(), order.getPizzas().get(0).getSize(), order.getPizzas().get(0).getSauce()));
        }
    }
}
