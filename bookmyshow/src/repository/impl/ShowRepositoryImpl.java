package repository.impl;

import entity.Show;
import repository.ShowRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ShowRepositoryImpl implements ShowRepository {
    private final Map<UUID, Show> showMap;

    public ShowRepositoryImpl() {
        this.showMap = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Show> findById(UUID showId) {
        return Optional.ofNullable(showMap.get(showId));
    }

    @Override
    public List<Show> findAllByScreenId(UUID screenId) {
        return showMap.values().stream()
                .filter(show -> show.getScreenId().equals(screenId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Show> findAllByMovieTitle(String movieTitle) {
        return showMap.values().stream()
                .filter(show -> show.getMovieTitle().toLowerCase().equals(movieTitle))
                .collect(Collectors.toList());
    }

    @Override
    public void save(Show show) {
        showMap.put(show.getShowId(), show);
    }
}
