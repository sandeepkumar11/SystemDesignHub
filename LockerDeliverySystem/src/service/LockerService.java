package service;

import enums.PackageSize;
import model.Locker;

public interface LockerService {

    Locker getLockerById(int lockerId);

    boolean lockLocker(int lockerId);

    boolean unlockLocker(int lockerId);

    boolean isLockerLocked(int lockerId);

    Locker getSuitableLockerId(PackageSize packageSize);

    boolean updateLocker(Locker locker);

    boolean checkPackageInLocker(int packageId);

}
