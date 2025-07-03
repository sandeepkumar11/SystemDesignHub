package repository;

import model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepository {

    List<Booking> getAllBookingsForRoom(String roomName);

    void addBooking(Booking booking);

    void removeBooking(String bookingId);

    Booking updateBooking(String bookingId, Booking updatedBooking);

    Optional<Booking> getBookingDetails(String bookingId);

    List<Booking> getAllBookings();

    void clearAllBookings();
}
