package edu.neumont.mgt101.controller;

import edu.neumont.mgt101.model.User;
import edu.neumont.mgt101.view.Console;
import edu.neumont.mgt101.view.FinanceUI;

public class FinanceController {
    public void run() {
        boolean running = true;

        while (running) {
            int selection = FinanceUI.displayMainMenu();

            switch (selection) {
                case 1 -> { // Create Account
                    String username = Console.getStringInput("Enter a username:", 3, 20, Console.TextColor.GREEN);
                    String password = Console.getStringInput("Enter a password:", 5, 30, Console.TextColor.GREEN);

                    User newUser = new User(username, password);
                    if (newUser.registerUser()) {
                        Console.writeLn("Account created successfully! Returning to main menu...");
                    } else {
                        Console.writeLn("Username already exists. Try again.");
                    }
                }
                case 2 -> { // Login
                    String username = Console.getStringInput("Enter your username:", 3, 20, Console.TextColor.YELLOW);
                    String password = Console.getStringInput("Enter your password:", 5, 30, Console.TextColor.YELLOW);
                    User loginUser = new User(username, password);

                    if (loginUser.authenticateUser()) {
                        Console.writeLn("Login successful! Welcome, " + username + "!");
                        handleLoggedInUser(loginUser);
                    } else {
                        Console.writeLn("Invalid username or password. Try again.");
                    }
                }
                case 3 -> { // Exit
                    Console.writeLn("Exiting system...");
                    running = false;
                }
            }
        }
    }

    private void handleLoggedInUser(User user) {
        boolean loggedIn = true;

        while (loggedIn) {
            int selection = FinanceUI.displayLoggedInMenu(); // Show logged-in menu

            switch (selection) {
                case 1 -> Console.writeLn("Deposit feature coming soon...");
                case 2 -> Console.writeLn("Withdraw feature coming soon...");
                case 3 -> Console.writeLn("View history feature coming soon...");
                case 4 -> {
                    Console.writeLn("Logging out...");
                    loggedIn = false;
                }
            }
        }
    }
}
