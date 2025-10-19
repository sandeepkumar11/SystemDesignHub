package entity;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Booking {
    private final UUID bookingId;
    private final UUID showId;
    private final String userId;
    private final String status;
    private final List<UUID> seatIds;
    private final Instant createdAt;

    public Booking(UUID bookingId, UUID showId, String userId, String status, List<UUID> seatIds) {
        this.bookingId = bookingId;
        this.showId = showId;
        this.userId = userId;
        this.status = status;
        this.seatIds = List.copyOf(seatIds); // defencive copy
        this.createdAt = Instant.now();
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public UUID getShowId() {
        return showId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }

    public List<UUID> getSeatIds() {
        return List.copyOf(seatIds);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
