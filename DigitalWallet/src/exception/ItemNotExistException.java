package exception;

public class ItemNotExistException extends RuntimeException{
    public ItemNotExistException(String message) {
        super(message);
    }

    public ItemNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemNotExistException(Throwable cause) {
        super(cause);
    }
}
