package entity;

import java.util.Set;

public class Group {
    private final String id;
    private final String name;
    private final String description;
    private final Set<String> members;

    public Group(String id, String name, String description, Set<String> members) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.members = members;
    }

    public Group(String id, String name, String description) {
        this(id, name, description, Set.of());
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

    public Set<String> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return "Group{id='" + id + "', name='" + name + "', description='" + description
                + "', members=" + members + "}";
    }
}
