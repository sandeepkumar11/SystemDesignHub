package entity;

import java.util.UUID;

public class Theatre {
    private final UUID theatreId;
    private final String name;
    private final UUID cityId;
    private final String address;

    public Theatre(UUID theatreId, String name, UUID cityId, String address) {
        this.theatreId = theatreId;
        this.name = name;
        this.cityId = cityId;
        this.address = address;
    }

    public UUID getTheatreId() {
        return theatreId;
    }

    public String getName() {
        return name;
    }

    public UUID getCityId() {
        return cityId;
    }

    public String getAddress() {
        return address;
    }
}
