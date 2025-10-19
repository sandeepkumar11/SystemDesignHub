package enums;

public enum SeatType {
    REGULAR("Regular"),
    PREMIUM("Premium");

    private final String displayName;

    SeatType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
