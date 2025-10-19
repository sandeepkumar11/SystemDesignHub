package repository;

import entity.Theatre;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TheatreRepository {
    Optional<Theatre> findById(UUID theatreId);

    List<Theatre> findAllByCityId(UUID cityId);

    Optional<Theatre> findByName(String name);

    void save(Theatre theatre);
}
