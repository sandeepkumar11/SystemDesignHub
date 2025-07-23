package model;

import java.time.LocalDateTime;

public class AuctionDto {
    private final String itemId;
    private final String userId;
    private final double startingPrice;
    private final double currentBid;
    private final String status;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;
    private final String description;

    private AuctionDto(Builder builder) {
        this.itemId = builder.itemId;
        this.userId = builder.userId;
        this.startingPrice = builder.startingPrice;
        this.currentBid = builder.currentBid;
        this.status = builder.status;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.description = builder.description;
    }

    public static class Builder {
        private String itemId;
        private String userId;
        private double startingPrice;
        private double currentBid;
        private String status;
        private LocalDateTime startTime;
        private LocalDateTime endTime;
        private String description;

        public Builder setItemId(String itemId) {
            this.itemId = itemId;
            return this;
        }

        public Builder setUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder setStartingPrice(double startingPrice) {
            this.startingPrice = startingPrice;
            return this;
        }

        public Builder setCurrentBid(double currentBid) {
            this.currentBid = currentBid;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
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

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AuctionDto build() {
            return new AuctionDto(this);
        }
    }

    public String getItemId() {
        return itemId;
    }

    public String getUserId() {
        return userId;
    }

    public double getStartingPrice() {
        return startingPrice;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public String getDescription() {
        return description;
    }

}
