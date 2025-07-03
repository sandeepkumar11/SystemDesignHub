package model;

public record BookingResponse(String bookingId, String roomName,
                              String startTime, String endTime,
                              String status) { }
