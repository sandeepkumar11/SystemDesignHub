package model;

import enums.LockerSize;

public class Locker {
    private final int lockerId;
    private final LockerSize lockerSize;
    private boolean isAvailable;
    private String lockerCode;
    private int packageId;

    public Locker(int lockerId, LockerSize lockerSize) {
        this.lockerId = lockerId;
        this.lockerSize = lockerSize;
        this.isAvailable = true;
        this.lockerCode = ""; // Default value indicating no code assigned
        this.packageId = -1;
    }

    public int getLockerId() {
        return lockerId;
    }

    public LockerSize getLockerSize() {
        return lockerSize;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getLockerCode() {
        return lockerCode;
    }

    public void setLockerCode(String lockerCode) {
        this.lockerCode = lockerCode;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    @Override
    public String toString() {
        return "Locker{" +
                "lockerId=" + lockerId +
                ", lockerSize=" + lockerSize +
                ", isAvailable=" + isAvailable +
                ", lockerCode=" + lockerCode +
                ", packageId=" + packageId +
                '}';
    }

}
