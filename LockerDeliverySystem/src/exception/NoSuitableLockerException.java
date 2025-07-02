package exception;

public class NoSuitableLockerException extends RuntimeException{
    public NoSuitableLockerException(String message) {
        super(message);
    }
}
