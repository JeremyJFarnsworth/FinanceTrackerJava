package edu.neumont.mgt101.view;

public class FinanceUI {

    public static int displayMainMenu() {
        return Console.getIntInput("""
                1. Create Account
                2. Login
                3. Exit
                """, 1, 3);
    }
    public static int displayLoggedInMenu() {
        return Console.getIntInput("""
                1. Deposit
                2. Withdraw
                3. View History
                4. Logout
                """, 1, 4);
    }
}
