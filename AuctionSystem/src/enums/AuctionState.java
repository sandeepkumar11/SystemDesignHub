package enums;

public enum AuctionState {
    NOT_STARTED,
    ACTIVE,
    ENDED,
    CANCELLED,
    SOLD,
    FAILED,
    REFUNDED;

    public static AuctionState fromString(String state) {
        return switch (state.toUpperCase()) {
            case "NOT_STARTED" -> NOT_STARTED;
            case "ACTIVE" -> ACTIVE;
            case "ENDED" -> ENDED;
            case "CANCELLED" -> CANCELLED;
            case "SOLD" -> SOLD;
            case "FAILED" -> FAILED;
            case "REFUNDED" -> REFUNDED;
            default -> throw new IllegalArgumentException("Unknown auction state: " + state);
        };
    }

    public boolean isActive() {
        return this == ACTIVE;
    }

    public boolean isEnded() {
        return this == ENDED || this == SOLD || this == FAILED || this == REFUNDED;
    }

    public boolean isCancelled() {
        return this == CANCELLED;
    }

    public boolean isRefunded() {
        return this == REFUNDED;
    }

    public boolean isSold() {
        return this == SOLD;
    }

    public boolean isNotStarted() {
        return this == NOT_STARTED;
    }

    public boolean isFailed() {
        return this == FAILED;
    }

    public boolean isSuccessful() {
        return this == SOLD;
    }
}
