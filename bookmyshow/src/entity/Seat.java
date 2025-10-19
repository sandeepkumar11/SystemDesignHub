package entity;

import java.util.UUID;

public class Seat {
    private final UUID seatId;
    private final UUID screenId;
    private final String row;
    private final int number;
    private final String type;
    private final double price;

    public Seat(UUID seatId, UUID screenId, String row, int number, String type, double price) {
        this.seatId = seatId;
        this.screenId = seatId;
        this.row = row;
        this.number = number;
        this.type = type;
        this.price = price;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public UUID getScreenId() {
        return screenId;
    }

    public String getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }
}
