package model;

import java.time.LocalDateTime;

public record BookingRequest(String bookingId, String roomName,
                             LocalDateTime startTime, LocalDateTime endTime) { }
