package repository;

import entity.Screen;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScreenRepository {
    Optional<Screen> findById(UUID screenId);

    Optional<Screen> findByName(String name);

    List<Screen> findAllByTheatreId(UUID theatreId);

    void save(Screen screen);
}
