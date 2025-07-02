package exception;

public class LockerNotFoundException extends RuntimeException {
    public LockerNotFoundException(String message) {
        super(message);
    }
}
