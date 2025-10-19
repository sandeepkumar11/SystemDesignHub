package entity;

import java.util.UUID;

public class Location {
    private final UUID cityId;
    private final String name;

    public Location(UUID cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public UUID getCityId() {
        return cityId;
    }

    public String getName() {
        return name;
    }
}
