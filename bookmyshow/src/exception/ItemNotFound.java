package exception;

public class ItemNotFound extends RuntimeException {
    public ItemNotFound(String message) {
        super(message);
    }

    public ItemNotFound() {
        super("Item Not Found");
    }

}
