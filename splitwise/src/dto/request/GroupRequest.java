package dto.request;

import java.util.List;

public class GroupRequest {
    private final String name;
    private final String description;
    private final List<String> members;

    public GroupRequest(String name, String description, List<String> members) {
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public GroupRequest(String name, String description) {
        this(name, description, List.of());
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getMembers() {
        return members;
    }
}
