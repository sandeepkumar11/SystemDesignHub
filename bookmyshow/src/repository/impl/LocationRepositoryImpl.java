package repository.impl;

import entity.Location;
import repository.LocationRepository;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class LocationRepositoryImpl implements LocationRepository {
    private final Map<UUID, Location> locationMap;

    public LocationRepositoryImpl() {
        locationMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Location> findById(UUID cityId) {
        return Optional.ofNullable(locationMap.get(cityId));
    }

    @Override
    public Optional<Location> findByName(String name) {
        return locationMap.values().stream().filter(
                        location -> location.getName().toLowerCase().equals(name))
                .findFirst();
    }

    @Override
    public void save(Location location) {
        locationMap.put(location.getCityId(), location);
    }
}
