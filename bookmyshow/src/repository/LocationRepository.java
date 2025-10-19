package repository;

import entity.Location;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepository {
    Optional<Location> findById(UUID cityId);

    Optional<Location> findByName(String name);

    void save(Location location);
}
