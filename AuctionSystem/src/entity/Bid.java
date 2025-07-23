package entity;

public class Bid {
    private String id;
    private Auction auction; // Many-to-One relationship with Auction
    private User user; // Many-to-One relationship with User
    private double amount;

    public Bid(String id, Auction auction, User user, double amount) {
        this.id = id;
        this.auction = auction;
        this.user = user;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "id='" + id + '\'' +
                ", auction=" + auction +
                ", user=" + user +
                ", amount=" + amount +
                '}';
    }
}
