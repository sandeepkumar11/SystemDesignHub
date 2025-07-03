package repository.impl;

import model.Booking;
import model.BookingRequest;
import model.BookingResponse;
import repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BookingRepositoryImpl implements BookingRepository {
    private  final Map<String, List<Booking>> bookingMap; // roomName -> List of bookings

    public BookingRepositoryImpl() {
        this.bookingMap = new ConcurrentHashMap<>();
    }

    @Override
    public List<Booking> getAllBookingsForRoom(String roomName){
        List<Booking> bookings = bookingMap.get(roomName);
        if (bookings == null) {
            return new ArrayList<>(); // Return empty list if no bookings exist for the room
        }
        return new ArrayList<>(bookings);
    }

    @Override
    public void addBooking(Booking booking) {
        String roomName = booking.roomName();
        bookingMap.computeIfAbsent(roomName, k -> new ArrayList<>()).add(booking);
    }

    @Override
    public Booking updateBooking(String bookingId, Booking updatedBooking){
        for (List<Booking> bookings : bookingMap.values()) {
            for (int i = 0; i < bookings.size(); i++) {
                Booking booking = bookings.get(i);
                if (booking.bookingId().equals(bookingId)) {
                    bookings.set(i, updatedBooking);
                    return updatedBooking; // Return the updated booking
                }
            }
        }
        return null; // Return null if no booking with the given ID was found
    }

    @Override
    public void removeBooking(String bookingId) {
        for (List<Booking> bookings : bookingMap.values()) {
            bookings.removeIf(booking -> booking.bookingId().equals(bookingId));
        }
    }

    @Override
    public Optional<Booking> getBookingDetails(String bookingId) {
        return bookingMap.values().stream()
                .flatMap(List::stream)
                .filter(booking -> booking.bookingId().equals(bookingId))
                .findFirst();
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingMap.values().stream()
                .flatMap(List::stream)
                .toList();
    }

    @Override
    public void clearAllBookings() {
        bookingMap.clear();
    }
}
