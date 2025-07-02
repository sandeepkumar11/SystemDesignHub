package enums;

public enum PackageSize {
    SMALL(1, "Small"),
    MEDIUM(2, "Medium"),
    LARGE(3, "Large"),
    EXTRA_LARGE(4, "Extra Large");

    private final int id;
    private final String description;

    PackageSize(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
