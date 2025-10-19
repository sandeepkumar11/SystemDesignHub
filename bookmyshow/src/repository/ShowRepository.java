package repository;

import entity.Show;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShowRepository {
    Optional<Show> findById(UUID showId);

    List<Show> findAllByScreenId(UUID screenId);

    List<Show> findAllByMovieTitle(String movieTitle);

    void save(Show show);
}
