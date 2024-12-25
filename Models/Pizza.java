package Models;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String pizzaId;
    private String name;
    private CrustType crust;
    private Size size;
    private SauceType sauce;
    private List<Topping> toppings;
    private List<Cheese> cheeses;
    private double price;

    public Pizza() {}

    public Pizza(String pizzaId, String name, CrustType crust, Size size, SauceType sauce) {
        this.pizzaId = pizzaId;
        this.name = name;
        this.crust = crust;
        this.size = size;
        this.sauce = sauce;
        this.toppings = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.price = crust.getPrice() + size.getPrice() + sauce.getPrice();
    }

    public String getPizzaId() {
        return pizzaId;
    }

    public String getName() {
        return name;
    }

    public CrustType getCrust() {
        return crust;
    }

    public Size getSize() {
        return size;
    }

    public SauceType getSauce() {
        return sauce;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public List<Cheese> getCheeses() {
        return cheeses;
    }

    public double getPrice() {
        return price;
    }

    public void addTopping(Topping topping) {
        toppings.add(topping);
        price += topping.getPrice();
    }

    public void addCheese(Cheese cheese) {
        cheeses.add(cheese);
        price += cheese.getPrice();
    }

    public void setPizzaId(String pizzaId) {
        this.pizzaId = pizzaId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCrustType(CrustType crust) {
        this.crust = crust;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public void setSauceType(SauceType sauce) {
        this.sauce = sauce;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public void setCheeses(List<Cheese> chesses) {
        this.cheeses = cheeses;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
