package exceptions;

public class ItemExistException extends RuntimeException{
    public ItemExistException(String message) {
        super(message);
    }

    public ItemExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemExistException(Throwable cause) {
        super(cause);
    }
}
