package repository.impl;

import entity.Theatre;
import repository.TheatreRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TheatreRepositoryImpl implements TheatreRepository {
    private final Map<UUID, Theatre> theatreMap;

    public TheatreRepositoryImpl() {
        theatreMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Theatre> findById(UUID theatreId) {
        return Optional.ofNullable(theatreMap.get(theatreId));
    }

    @Override
    public List<Theatre> findAllByCityId(UUID cityId) {
        return theatreMap.values().stream()
                .filter(theatre -> theatre.getCityId().equals(cityId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Theatre> findByName(String name) {
        return theatreMap.values().stream()
                .filter(theatre -> theatre.getName().toLowerCase().equals(name))
                .findFirst();
    }

    @Override
    public void save(Theatre theatre) {
        theatreMap.put(theatre.getTheatreId(), theatre);
    }
}
