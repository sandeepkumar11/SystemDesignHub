package service;

import model.Booking;
import model.BookingRequest;
import model.BookingResponse;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {
    void addBooking(String bookingId, String roomName, LocalDateTime startTime, LocalDateTime endTime);

    void cancelBooking(String bookingId);

    BookingResponse getBookingDetails(String bookingId);

    BookingResponse updateBooking(String bookingId, BookingRequest bookingRequest);

    List<BookingResponse> getAllBookings();

    boolean isRoomAvailable(String name, LocalDateTime startTime, LocalDateTime endTime);

    List<BookingResponse> getAllBookingsForRoom(String roomName);

    void clearAllBookings();
}
