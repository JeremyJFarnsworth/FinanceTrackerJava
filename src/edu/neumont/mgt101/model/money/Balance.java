package edu.neumont.mgt101.model.money;

public class Balance {
    private int balance;

    public int viewBalance() {
        return balance;
    }

    private void setBalance(int balance) {
        this.balance = balance;
    }

    protected void withdraw(int balance) {
        if (balance > viewBalance()) {
            balance = 0; // something to notify the user that they cannot withdraw more money than they have
        }
        setBalance(viewBalance() - balance);
    }

    protected void deposit(int balance) {
        setBalance(viewBalance() + balance);
    }
}
