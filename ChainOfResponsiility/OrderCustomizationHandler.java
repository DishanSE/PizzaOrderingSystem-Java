package ChainOfResponsiility;

import OrderManagement.Order;

public abstract class OrderCustomizationHandler {
    private OrderCustomizationHandler nextHandler;

    public void setNextHandler(OrderCustomizationHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handleRequest(Order order) {
        if (nextHandler != null) {
            nextHandler.handleRequest(order);
        }
    }

    protected abstract void processRequest(Order order);
}
