package scratch;

import javax.naming.InsufficientResourcesException;

public class Account {
    private int balance;
    private String name;

    public Account(String name) {
        this.balance = 0;
        this.name = name;
    }

    public void deposit(int money) {
        this.balance += money;
    }

    public void withdraw(int money) {
        if(balance-money<0) throw new IllegalArgumentException();
        this.balance -= money;
    }

    public int getBalance() {
        return balance;
    }

    public boolean hasPositiveBalance() {
        if(balance>0) return true;
        return false;
    }
}
