package repository.impl;

import entity.Seat;
import repository.SeatRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SeatRepositoryImpl implements SeatRepository {
    private final Map<UUID, Seat> seatMap;

    public SeatRepositoryImpl() {
        this.seatMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Seat> findById(UUID seatId) {
        return Optional.ofNullable(seatMap.get(seatId));
    }

    @Override
    public List<Seat> findAllByScreenId(UUID screenId) {
        return seatMap.values().stream()
                .filter(seat -> seat.getScreenId().equals(screenId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Seat> findAllByType(String type) {
        return seatMap.values().stream()
                .filter(seat -> seat.getType().equals(type))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Seat> findByNumber(int number) {
        return seatMap.values().stream()
                .filter(seat -> seat.getNumber() == number).findFirst();
    }

    @Override
    public void save(Seat seat) {
        seatMap.put(seat.getSeatId(), seat);
    }
}
