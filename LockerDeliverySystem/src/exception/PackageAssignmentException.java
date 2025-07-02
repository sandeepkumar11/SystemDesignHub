package exception;

public class PackageAssignmentException extends RuntimeException{
    public PackageAssignmentException(String message) {
        super(message);
    }

    public PackageAssignmentException(String message, Throwable cause) {
        super(message, cause);
    }
}
