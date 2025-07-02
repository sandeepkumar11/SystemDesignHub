package model;

public record LockerResponse(int lockerId,String lockerCode) {
    public LockerResponse {
        if (lockerId <= 0 || lockerCode == null || lockerCode.isEmpty()) {
            throw new IllegalArgumentException("Locker ID must be a positive integer and locker code cannot be null or empty.");
        }
    }
}
