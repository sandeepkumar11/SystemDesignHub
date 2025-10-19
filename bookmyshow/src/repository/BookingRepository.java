package repository;

import entity.Booking;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BookingRepository {
    Optional<Booking> findById(UUID bookingId);

    List<Booking> findAllByShowId(UUID showId);

    Optional<Booking> findByUserId(String userId);

    List<Booking> findAllByStatus(String status);

    void save(Booking booking);
}
