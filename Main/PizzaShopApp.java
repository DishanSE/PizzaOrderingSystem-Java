package Main;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import BuilderPattern.PizzaOrderBuilder;
import CommandPattern.PlaceOrderCommand;
import FeedbackAndRatings.Feedback;
import FeedbackAndRatings.FeedbackManager;
import Models.Cheese;
import Models.CrustType;
import Models.Pizza;
import Models.SauceType;
import Models.Size;
import Models.Topping;
import Models.User;
import OrderManagement.Order;
import OrderManagement.OrderStatus;
import OrderTracking.OrderTracker;
import PaymentAndLoyalty.CreditCardPayment;
import PaymentAndLoyalty.DigitalWalletPayment;
import PaymentAndLoyalty.LoyaltyProgram;
import PaymentAndLoyalty.PaymentProcessor;
import PaymentAndLoyalty.PaymentStrategy;
import PromotionsAndSeasonalSpecials.Promotion;
import PromotionsAndSeasonalSpecials.PromotionManager;
import UserProfileManagement.UserProfileManager;

public class PizzaShopApp {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        
        // Initialize components
        UserProfileManager userProfileManager = new UserProfileManager();
        OrderTracker orderTracker = new OrderTracker();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram();
        PromotionManager promotionManager = new PromotionManager();
        FeedbackManager feedbackManager = new FeedbackManager();

        // Register payment strategies
        paymentProcessor.addPaymentStrategy("creditCard", new CreditCardPayment());
        paymentProcessor.addPaymentStrategy("digitalWallet", new DigitalWalletPayment());

        // Register promotions
        promotionManager.addPromotion(new Promotion("Summer Special", 0.1, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 8, 31)));

        while (true) {
            System.out.println("1. Register User");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerUser(userProfileManager, sc);
                    break;
                case 2:
                    User loggedInUser = loginUser(userProfileManager, sc);
                    if (loggedInUser != null) {
                        userMenu(loggedInUser, userProfileManager, orderTracker, paymentProcessor, loyaltyProgram, promotionManager, feedbackManager, sc);
                    }
                    break;
                case 3:
                    System.out.println("Have a nice day... :)");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void registerUser(UserProfileManager userProfileManager, Scanner sc) {
        String userId = getValidInput("Enter user ID: ", "[a-zA-Z0-9]{6,12}", 
                        "Invalid User ID. It must be alphanumeric and 6-12 characters long.");
        
        String userName = getValidInput("Enter user name: ", "[a-zA-Z ]+", 
                        "Invalid User Name. It must contain only letters and spaces.");

        String email = getValidInput("Enter Email: ", "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$", 
                        "Invalid Email. Please enter a valid email address.");
        
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        if (!isValidPassword(password)) {
            System.out.println(
                "Invalid Password. It must be at least 8 characters long, contain an uppercase letter, a lowercase letter, a digit, and a special character.");
            return;
        }

        try {
            User user = new User(userId, userName, email, password);
            userProfileManager.registerUser(user);
            System.out.println("User registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getValidInput(String prompt, String regex, String errorMessage) {
        
        String input;
        do {
            System.out.print(prompt);
            input = sc.nextLine().trim();
            if (input.isEmpty() || !input.matches(regex)) {
                System.out.println(errorMessage);
            }
        } while (input.isEmpty() || !input.matches(regex));
        return input;
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               password.matches(".*[A-Z].*") &&
               password.matches(".*[a-z].*") &&
               password.matches(".*\\d.*") &&
               password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");
    }

    private static User loginUser(UserProfileManager userProfileManager, Scanner sc) {
        System.out.print("Enter user ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try {
            User user = userProfileManager.loginUser(userId, password);
            System.out.println("Login successful.");
            return user;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static void userMenu(User user, UserProfileManager userProfileManager, OrderTracker orderTracker, PaymentProcessor paymentProcessor, LoyaltyProgram loyaltyProgram, PromotionManager promotionManager, FeedbackManager feedbackManager, Scanner sc) {
        while (true) {
            System.out.println("1. Place Order");
            System.out.println("2. View Order Status");
            System.out.println("3. Provide Feedback");
            System.out.println("4. View Loyalty Points");
            System.out.println("5. View Favorite Pizzas");
            System.out.println("6. Reorder Favorite Pizza");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    placeOrder(user, userProfileManager, orderTracker, paymentProcessor, loyaltyProgram, promotionManager, sc);
                    break;
                case 2:
                    viewOrderStatus(user, orderTracker, sc);
                    break;
                case 3:
                    provideFeedback(user, orderTracker, feedbackManager, sc);
                    break;
                case 4:
                    viewLoyaltyPoints(user, loyaltyProgram);
                    break;
                case 5:
                    viewFavoritePizzas(user);
                    break;
                case 6:
                    reorderFavoritePizza(user, userProfileManager, sc);
                    break;
                case 7:
                    System.out.println("Logged out.");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void placeOrder(User user, UserProfileManager userProfileManager, OrderTracker orderTracker, PaymentProcessor paymentProcessor, LoyaltyProgram loyaltyProgram, PromotionManager promotionManager, Scanner sc) {
        PizzaOrderBuilder orderBuilder = new PizzaOrderBuilder(user, false, "");
        while (true) {
            System.out.println("1. Add Pizza");
            System.out.println("2. Review Order");
            System.out.println("3. Cancel");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addPizzaToOrder(orderBuilder, sc);
                    break;
                case 2:
                    Order order = orderBuilder.build();
                    System.out.println("Order Summary:");
                    for (Pizza pizza : order.getPizzas()) {
                        System.out.println("Pizza: " + pizza.getName() + " - " + pizza.getPrice());
                        System.out.println("    Crust: " + pizza.getCrust().getName() + " - " + pizza.getCrust().getPrice());
                        System.out.println("    Size: " + pizza.getSize().getName() + " - " + pizza.getSize().getPrice());
                        System.out.println("    Sauce: " + pizza.getSauce().getName() + " - " + pizza.getSauce().getPrice());
                        System.out.println("    Toppings: " + String.join(", ", pizza.getToppings().stream().map(Topping::getName).toArray(String[]::new)));
                        System.out.println("    Cheeses: " + String.join(", ", pizza.getCheeses().stream().map(Cheese::getName).toArray(String[]::new)));
                    }
                    System.out.println("Total Price: " + order.getTotalPrice());

                    System.out.println("Select delivery option: 1. Pickup, 2. Delivery");
                    String deliveryOption = sc.nextInt() == 1 ? "Pickup" : "Delivery";
                    sc.nextLine();
                    if (deliveryOption.equalsIgnoreCase("delivery")) {
                        System.out.print("Enter delivery address: ");
                        String deliveryAddress = sc.nextLine();
                        order.setDelivery(true);
                        order.setDeliveryAddress(deliveryAddress);
                    }

                    System.out.print("Confirm order (y/n): ");
                    String confirm = sc.nextLine();
                    if (confirm.equalsIgnoreCase("y")) {
                        placeOrderCommand(order, orderTracker, paymentProcessor, loyaltyProgram, promotionManager, sc);
                    }
                    return;
                case 3:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addPizzaToOrder(PizzaOrderBuilder orderBuilder, Scanner scanner) {
        System.out.print("Enter pizza name: ");
        String name = scanner.nextLine();

        System.out.println("Choose crust type:");
        System.out.println("1. Thin Crust");
        System.out.println("2. Thick Crust");
        System.out.println("3. Stuffed Crust");
        System.out.print("Enter choice: ");
        int crustChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        CrustType crust = getCrustType(crustChoice);

        System.out.println("Choose pizza size:");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        System.out.print("Enter choice: ");
        int sizeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Size size = getSize(sizeChoice);

        System.out.println("Choose sauce:");
        System.out.println("1. Tomato Sauce");
        System.out.println("2. Pesto");
        System.out.println("3. White Sauce");
        System.out.println("4. BBQ Sauce");
        System.out.print("Enter choice: ");
        int sauceChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        SauceType sauce = getSauceType(sauceChoice);

        Pizza pizza = new Pizza("custom1", name, crust, size, sauce);

        System.out.print("\nAdd toppings (y/n): ");
        String addToppings = scanner.nextLine();
        if (addToppings.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Choose topping:");
                System.out.println("1. Pepperoni");
                System.out.println("2. Mushroom");
                System.out.println("3. Olive");
                System.out.println("4. Bacon");
                System.out.println("5. Onion");
                System.out.println("6. Tomatoes");
                System.out.println("7. BBQ Chicken");
                System.out.println("8. Sausage");
                System.out.print("Enter choice: ");
                int toppingChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Topping topping = getTopping(toppingChoice);
                pizza.addTopping(topping);

                System.out.print("Add another topping (y/n): ");
                String addAnother = scanner.nextLine();
                if (!addAnother.equalsIgnoreCase("y")) {
                    break;
                }
            }
        }

        System.out.print("\nAdd cheese (y/n): ");
        String addCheese = scanner.nextLine();
        if (addCheese.equalsIgnoreCase("y")) {
            while (true) {
                System.out.println("Choose cheese:");
                System.out.println("1. Mozzarella");
                System.out.println("2. Cheddar");
                System.out.println("3. Goat");
                System.out.println("4. Parmesan");
                System.out.print("Enter choice: ");
                int cheeseChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                Cheese cheese = getCheese(cheeseChoice);
                pizza.addCheese(cheese);

                System.out.print("Add another cheese (y/n): ");
                String addAnother = scanner.nextLine();
                if (!addAnother.equalsIgnoreCase("y")) {
                    break;
                }
            }
        }

        orderBuilder.addPizza(pizza);
    }

    private static void placeOrderCommand(Order order, OrderTracker orderTracker, PaymentProcessor paymentProcessor, LoyaltyProgram loyaltyProgram, PromotionManager promotionManager, Scanner sc) {
        // Apply promotions
        List<Promotion> activePromotions = promotionManager.getActivePromotions();
        for (Promotion promotion : activePromotions) {
            order.setTotalPrice(order.getTotalPrice() * (1 - promotion.getDiscount()));
            System.out.println("Applied promotion: " + promotion.getName() + " - " + (promotion.getDiscount() * 100) + "% off");
        }

        // Place the order
        PlaceOrderCommand placeOrderCommand = new PlaceOrderCommand(order, orderTracker);
        placeOrderCommand.execute();

        // Process payment
        System.out.println("Choose payment method: 1. Credit Card 2. Digital Wallet");
        String choice = sc.nextLine(); // Read input as a string
        String paymentMethod = choice.equals("1") ? "creditCard" : choice.equals("2") ? "digitalWallet" : null;

        if (paymentMethod != null) {
            PaymentStrategy strategy = paymentProcessor.paymentStrategies.get(paymentMethod);
            if (strategy != null) {
                strategy.processPayment(order);
            } else {
                System.out.println("Payment method not supported.");
            }
        } else {
            System.out.println("Invalid payment method.");
        }


        // Update order status
        orderTracker.updateOrderStatus(order, OrderStatus.IN_PREPARATION);
        try {
            Thread.sleep(3000);
            System.out.println("Your Order in preparation.");
            orderTracker.updateOrderStatus(order, OrderStatus.READY);
            Thread.sleep(3000);
            System.out.println("Your Order Ready.");
            if (order.isDelivery()) {
                orderTracker.updateOrderStatus(order, OrderStatus.OUT_FOR_DELIVERY);
                Thread.sleep(3000);
                System.out.println("Your Order out for delivery.");
                Thread.sleep(3000);
                System.out.println("Your Order delivered.");
            } else {
                System.out.println("You can collect your order.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Add to favorites
        System.out.print("Add this pizza to favorites (y/n): ");
        String addFavorite = sc.nextLine();
        if (addFavorite.equalsIgnoreCase("y")) {
            UserProfileManager.saveFavouritePizza(order.getUser(), order.getPizzas().get(0));
        }

        // Loyalty points
        loyaltyProgram.awardPoints(order.getUser(), 100);
        System.out.println("Loyalty points for " + order.getUser().getUserName() + ": " + loyaltyProgram.getPoints(order.getUser()));
    }

    private static void viewOrderStatus(User user, OrderTracker orderTracker, Scanner scanner) {
        System.out.print("Enter order ID: ");
        String orderId = scanner.nextLine();
        Order order = orderTracker.getOrder(orderId);
        if (order != null && order.getUser().equals(user)) {
            System.out.println("Order " + orderId + " status: " + order.getStatus());
        } else {
            System.out.println("Order not found or does not belong to you.");
        }
    }

    private static void provideFeedback(User user, OrderTracker orderTracker, FeedbackManager feedbackManager, Scanner sc) {
        System.out.print("Enter order ID: ");
        String orderId = sc.nextLine();
        Order order = orderTracker.getOrder(orderId);
        if (order != null && order.getUser().equals(user)) {
            System.out.print("Enter feedback: ");
            String feedback = sc.nextLine();
            System.out.print("Enter rating (1-5): ");
            int rating = sc.nextInt();
            sc.nextLine(); // Consume newline

            Feedback feedbackObj = new Feedback(orderId, feedback, rating);
            feedbackManager.addFeedback(feedbackObj);
            System.out.println("Feedback for order " + orderId + " added successfully.");
        } else {
            System.out.println("Order not found or does not belong to you.");
        }
    }

    private static void viewLoyaltyPoints(User user, LoyaltyProgram loyaltyProgram) {
        System.out.println("Loyalty points for " + user.getUserName() + ": " + loyaltyProgram.getPoints(user));
    }

    private static void viewFavoritePizzas(User user) {
        List<Pizza> favoritePizzas = user.getFavouritePizzas();
        for (Pizza pizza : favoritePizzas) {
            System.out.println("Favorite Pizza: " + pizza.getName() + " - " + pizza.getPrice());
        }
    }

    private static void reorderFavoritePizza(User user, UserProfileManager userProfileManager, Scanner sc) {
        List<Pizza> favoritePizzas = user.getFavouritePizzas();
        if (favoritePizzas.isEmpty()) {
            System.out.println("No favorite pizzas found.");
            return;
        }

        System.out.println("Choose a favorite pizza to reorder:");
        for (int i = 0; i < favoritePizzas.size(); i++) {
            System.out.println((i + 1) + ". " + favoritePizzas.get(i).getName());
        }
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (choice > 0 && choice <= favoritePizzas.size()) {
            Pizza pizza = favoritePizzas.get(choice - 1);
            userProfileManager.recorderFavouritePizza(user, pizza);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private static CrustType getCrustType(int choice) {
        switch (choice) {
            case 1:
                return new CrustType("Thin Crust", 100.0);
            case 2:
                return new CrustType("Thick Crust", 150.0);
            case 3:
                return new CrustType("Stuffed Crust", 200.0);
            default:
                throw new IllegalArgumentException("Invalid crust choice.");
        }
    }

    private static Size getSize(int choice) {
        switch (choice) {
            case 1:
                return new Size("Small", 800.0);
            case 2:
                return new Size("Medium", 1500.0);
            case 3:
                return new Size("Large", 2500.0);
            default:
                throw new IllegalArgumentException("Invalid size choice.");
        }
    }

    private static SauceType getSauceType(int choice) {
        switch (choice) {
            case 1:
                return new SauceType("Tomato Sauce", 150.0);
            case 2:
                return new SauceType("Pesto", 200.0);
            case 3:
                return new SauceType("White Sauce", 150.0);
            case 4:
                return new SauceType("BBQ Sauce", 200.0);
            default:
                throw new IllegalArgumentException("Invalid sauce choice.");
        }
    }

    private static Topping getTopping(int choice) {
        switch (choice) {
            case 1:
                return new Topping("Pepperoni", 100.0);
            case 2:
                return new Topping("Mushroom", 150.0);
            case 3:
                return new Topping("Olive", 150.0);
            case 4:
                return new Topping("Bacon", 300.0);
            case 5:
                return new Topping("Onion", 100.0);
            case 6:
                return new Topping("Tomatoes", 100.0);
            case 7:
                return new Topping("BBQ Chicken", 350.0);
            case 8:
                return new Topping("Sausage", 250.0);
            default:
                throw new IllegalArgumentException("Invalid topping choice.");
        }
    }

    private static Cheese getCheese(int choice) {
        switch (choice) {
            case 1:
                return new Cheese("Mozzarella", 200.0);
            case 2:
                return new Cheese("Cheddar", 150.0);
            case 3:
                return new Cheese("Goat", 200.0);
            case 4:
                return new Cheese("Parmesan", 150.0);
            default:
                throw new IllegalArgumentException("Invalid cheese choice.");
        }
    }
}