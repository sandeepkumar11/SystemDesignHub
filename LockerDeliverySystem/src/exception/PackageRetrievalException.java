package exception;

public class PackageRetrievalException extends RuntimeException{
    public PackageRetrievalException(String message) {
        super(message);
    }

    public PackageRetrievalException(String message, Throwable cause) {
        super(message, cause);
    }
}
