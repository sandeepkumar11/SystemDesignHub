package service;

import enums.PackageSize;
import model.LockerResponse;
import model.Package;

public interface PackageService {

    LockerResponse assignPackageToLocker(int packageId, PackageSize packageSize);

    Package retrievePackageFromLocker(int lockerId, String lockerCode);

    boolean isPackageAssignedToLocker(int packageId);

    int returnPackageToLocker(int packageId);

}
