package entity;

import enums.AuctionState;

import java.time.LocalDateTime;

public class Auction {
    private String id;
    private Item item;
    private User owner;
    private User highestBidder;
    private double startingPrice;
    private double highestBidAmount;
    private AuctionState status;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private Auction(Builder builder) {
        this.id = builder.id;
        this.item = builder.item;
        this.owner = builder.owner;
        this.startingPrice = builder.startingPrice;
        this.description = builder.description;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.status = builder.status;
        this.highestBidAmount = startingPrice; // Initial current price is the starting price
    }

    public static class Builder {
        private String id;
        private Item item;
        private User owner;
        private double startingPrice;
        private AuctionState status;
        private String description;
        private LocalDateTime startTime;
        private LocalDateTime endTime;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setItem(Item item) {
            this.item = item;
            return this;
        }

        public Builder setOwner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder setStartingPrice(double startingPrice) {
            this.startingPrice = startingPrice;
            return this;
        }

        public Builder setStatus(AuctionState status) {
            this.status = status;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setStartTime(LocalDateTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Auction build() {
            return new Auction(this);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getHighestBidder() {
        return highestBidder;
    }

    public void setHighestBidder(User highestBidder) {
        this.highestBidder = highestBidder;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    public double getHighestBidAmount() {
        return highestBidAmount;
    }

    public void setHighestBidAmount(double highestBidAmount) {
        this.highestBidAmount = highestBidAmount;
    }

    public AuctionState getStatus() {
        return status;
    }

    public void setStatus(AuctionState status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Auction{" +
                "id='" + id + '\'' +
                ", item=" + item +
                ", owner=" + owner +
                ", highestBidder=" + highestBidder +
                ", startingPrice=" + startingPrice +
                ", currentPrice=" + highestBidAmount +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
