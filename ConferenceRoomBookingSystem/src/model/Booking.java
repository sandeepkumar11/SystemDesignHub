package model;

import enums.BookingStatus;

import java.time.LocalDateTime;

public record Booking(String bookingId, String roomName,
                      LocalDateTime startTime, LocalDateTime endTime,
                      String status) {
}
