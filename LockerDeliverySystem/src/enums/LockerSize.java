package enums;

public enum LockerSize {
    SMALL(1, 1, 1),
    MEDIUM(4, 4, 4),
    LARGE(6, 6, 6);

    private final int width;
    private final int height;
    private final int depth;

    LockerSize(int width, int height, int depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }
}
