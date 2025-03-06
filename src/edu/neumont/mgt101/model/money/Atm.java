package edu.neumont.mgt101.model.money;

import java.util.ArrayList;

public class Atm {
    private Balance balance;
    private ArrayList<Integer> transactionList;

    public Atm() {
        balance = new Balance();
        transactionList = new ArrayList<>();
    }

    public void withdraw(int amount) {
        balance.withdraw(amount);
        transactionList.add(-1 * amount);
    }

    public void deposit(int amount) {
        balance.deposit(amount);
        transactionList.add(amount);
    }
}
