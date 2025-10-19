package repository.impl;

import entity.Screen;
import repository.ScreenRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ScreenRepositoryImpl implements ScreenRepository {
    private final Map<UUID, Screen> screenMap;

    public ScreenRepositoryImpl() {
        this.screenMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Screen> findById(UUID screenId) {
        return Optional.ofNullable(screenMap.get(screenId));
    }

    @Override
    public Optional<Screen> findByName(String name) {
        return screenMap.values().stream()
                .filter(screen -> screen.getName().toLowerCase().equals(name))
                .findFirst();
    }

    @Override
    public List<Screen> findAllByTheatreId(UUID theatreId) {
        return screenMap.values().stream().filter(screen -> screen.getTheatreId().equals(theatreId))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Screen screen) {
        screenMap.put(screen.getScreenId(), screen);
    }
}
