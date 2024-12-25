package DecoratorPattern;

import OrderManagement.Order;

public class SpecialPackagingOrder extends EnhancedOrder {
    private String packagingType;

    public SpecialPackagingOrder(Order order, String packagingType) {
        super(order);
        this.packagingType = packagingType;
    }

    @Override
    public void addFeature() {
        // add special packaging to the order 
        System.out.println("Special packaging (" + packagingType + ") added to order " + order.getOrderId());
    }
}
