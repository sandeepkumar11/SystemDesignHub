package repository.impl;

import entity.Booking;
import repository.BookingRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class BookingRepositoryImpl implements BookingRepository {
    private final Map<UUID, Booking> bookingMap;

    public BookingRepositoryImpl() {
        this.bookingMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Booking> findById(UUID bookingId) {
        return Optional.ofNullable(bookingMap.get(bookingId));
    }

    @Override
    public List<Booking> findAllByShowId(UUID showId) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getShowId().equals(showId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Booking> findByUserId(String userId) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getUserId().equals(userId))
                .max(Comparator.comparing(Booking::getCreatedAt));
    }

    @Override
    public List<Booking> findAllByStatus(String status) {
        return bookingMap.values().stream()
                .filter(booking -> booking.getStatus().equals(status))
                .toList();
    }

    @Override
    public void save(Booking booking) {
        bookingMap.put(booking.getBookingId(), booking);
    }
}
