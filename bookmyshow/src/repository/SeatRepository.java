package repository;

import entity.Seat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeatRepository {
    Optional<Seat> findById(UUID seatId);

    List<Seat> findAllByScreenId(UUID screenId);

    List<Seat> findAllByType(String type);

    Optional<Seat> findByNumber(int number);

    void save(Seat seat);
}
