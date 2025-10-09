package entity;

public class UserBalance {
    private final String userId;
    private double balance;

    public UserBalance(String userId) {
        this.userId = userId;
        this.balance = 0.0;
    }

    public String getUserId() {
        return userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
