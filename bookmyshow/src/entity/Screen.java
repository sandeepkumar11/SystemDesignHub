package entity;

import java.util.UUID;

public class Screen {
    private final UUID screenId;
    private final String name;
    private final UUID theatreId;

    public Screen(UUID screenId, String name, UUID theatreId) {
        this.screenId = screenId;
        this.name = name;
        this.theatreId = theatreId;
    }

    public UUID getScreenId() {
        return screenId;
    }

    public String getName() {
        return name;
    }

    public UUID getTheatreId() {
        return theatreId;
    }

}
