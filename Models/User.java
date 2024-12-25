package Models;

import java.util.*;

public class User {
    private String userId;
    private String userName;
    private String email;
    private String password;
    private List<Pizza> favouritePizzas;

    public User() {}

    public User(String userId, String userName, String email, String password) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.favouritePizzas = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Pizza> getFavouritePizzas() {
        return favouritePizzas;
    }

    public void addFavouritePizza(Pizza pizza) {
        favouritePizzas.add(pizza);
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFavouritePizzas(List<Pizza> favouritePizzas) {
        this.favouritePizzas = favouritePizzas;
    }
}



