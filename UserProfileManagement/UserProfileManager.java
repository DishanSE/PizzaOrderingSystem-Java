package UserProfileManagement;

import java.util.*;

import Models.Pizza;
import Models.User;

public class UserProfileManager {
    private Map<String, User> users = new HashMap<>();

    public void registerUser(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new IllegalArgumentException("User already exists.");
        }
        users.put(user.getUserId(), user);
    }

    public User loginUser(String userId, String password) {
        User user = users.get(userId);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid user credentials");
        }
        return user;
    }

    public static void saveFavouritePizza(User user, Pizza pizza) {
        user.addFavouritePizza(pizza);
    }

    public void recorderFavouritePizza(User user, Pizza pizza) {
        System.out.println("Recording favourite pizza: " + pizza.getName());
    }
}
