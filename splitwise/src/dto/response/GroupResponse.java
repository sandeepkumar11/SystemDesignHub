package dto.response;

public class GroupResponse {
    private final String id;
    private final String name;
    private final String description;
    private final String members; // Comma-separated member names

    public GroupResponse(String id, String name, String description, String members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "GroupResponse{id='" + id + "', name='" + name
                + "', description='" + description + "', members='" + members + "'}";
    }
}
