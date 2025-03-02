# Pizza Ordering System

## Overview

The Pizza Ordering System is a Java-based application that allows users to register, log in, customize and place pizza orders, track their orders in real-time, and provide feedback. The system also includes features such as a loyalty program and flexible payment options.

## Features

- **User Management**: Registration and login with secure credential handling.
- **Order Customization**: Customizable pizzas with options for crust type, size, sauce, toppings, and cheese.
- **Order Placement**: Support for both pickup and delivery options, with the ability to specify the number of pizzas.
- **Real-Time Tracking**: Real-time order status updates using the Observer pattern.
- **Payment Processing**: Flexible payment methods using the Strategy pattern.
- **Loyalty Program**: Awarding loyalty points for each order.
- **Feedback System**: Users can provide feedback and ratings for their orders.

## Architecture

The system is designed using several design patterns to ensure robustness and maintainability:

- **Builder Pattern**: For constructing complex order objects.
- **Observer Pattern**: For real-time order status updates.
- **Strategy Pattern**: For flexible payment processing.
- **Chain of Responsibility Pattern**: For modular order customization.
- **State Pattern**: For managing order state transitions.
- **Command Pattern**: For encapsulating requests as objects.
- **Decorator Pattern**: For dynamically adding features to orders.

## Setup

### Prerequisites

- Java Development Kit (JDK) 11 or later
- Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse
- Git (optional for cloning the repository)

### Cloning the Repository

```sh
git clone https://github.com/yourusername/pizza-ordering-system.git
cd pizza-ordering-system

### Running the Application

1. Open the project in your IDE.
2. Run the `PizzaShopApp` class to start the application.
3. Follow the on-screen instructions to register, log in, and place orders.

### Example Usage

1. **Register a User:**
   - Enter user ID, name, email, and password.
2. **Log In:**
   - Enter user ID and password.
3. **Place an Order:**
   - Choose delivery option (pickup or delivery).
   - Specify the number of pizzas.
   - Customize each pizza (crust type, size, sauce, toppings, cheese).
   - Confirm the order.
4. **Track Order:**
   - Enter the order ID to view the current status.
5. **Provide Feedback:**
   - Enter the order ID, feedback, and rating.
6. **View Loyalty Points:**
   - Enter user ID to view loyalty points.
7. **View Favorite Pizzas:**
   - View the list of favorite pizzas.
8. **Reorder Favorite Pizza:**
   - Choose a favorite pizza to reorder.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

```
