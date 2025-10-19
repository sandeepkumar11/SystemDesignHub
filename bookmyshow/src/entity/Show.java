package entity;

import java.time.Instant;
import java.util.UUID;

public class Show {
    private final UUID showId;
    private final UUID screenId;
    private final String movieTitle;
    private final Instant startTime;
    private final Instant endTime;

    public Show(UUID showId, UUID screenId, String movieTitle, Instant startTime, Instant endTime) {
        this.showId = showId;
        this.screenId = screenId;
        this.movieTitle = movieTitle;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getShowId() {
        return showId;
    }

    public UUID getScreenId() {
        return screenId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }
}
