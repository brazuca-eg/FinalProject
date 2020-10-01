package ua.kharkov.repairagency.db.entity;

public class Balance extends Entity{
    private double balance;

    public Balance() {

    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "balance=" + balance +
                '}';
    }
}
