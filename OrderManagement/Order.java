package OrderManagement;

import java.time.LocalDateTime;
import java.util.*;
import Models.*;
import StatePattern.OrderState;

public class Order {
    private String orderId;
    private User user;
    private List<Pizza> pizzas;
    private OrderStatus status;
    private boolean isDelivery;
    private String deliveryAddress;
    private double totalPrice;
    private LocalDateTime orderTime;

    public Order() {}
    
    public Order(String orderId, User user, boolean isDelivery, String deliveryAddress) {
        this.orderId = orderId;
        this.user = user;
        this.isDelivery = isDelivery;
        this.deliveryAddress = deliveryAddress;
        this.pizzas = new ArrayList<>();
        this.status = OrderStatus.PLACED;
        this.totalPrice = 0.0;
        this.orderTime = LocalDateTime.now();
    }

    public String getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public boolean getIsDelivery() {
        return isDelivery;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime orderTime() {
        return orderTime;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setIsdelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public void setState(OrderState state) {
        this.status = state.getStatus();
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
        totalPrice += pizza.getPrice();
    }

    public boolean isDelivery() {
        return isDelivery;
    }

    public void setDelivery(boolean isDelivery) {
        this.isDelivery = isDelivery;
    }
}
