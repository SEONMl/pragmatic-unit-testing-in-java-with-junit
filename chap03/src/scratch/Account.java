package scratch;

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
