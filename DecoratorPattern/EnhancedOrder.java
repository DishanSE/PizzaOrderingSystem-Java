package DecoratorPattern;

import OrderManagement.Order;

public abstract class EnhancedOrder {
    protected Order order;
    
    public EnhancedOrder(Order order) {
        this.order = order;
    }

    public abstract void addFeature();
}

