package service.impl;

import enums.PackageSize;
import exception.PackageAssignmentException;
import exception.PackageNotFoundException;
import exception.PackageRetrievalException;
import model.Locker;
import model.LockerResponse;
import model.Package;
import repository.PackageRepository;
import service.LockerService;
import service.PackageService;
import strategy.LockerCodeGenerator;

import java.util.concurrent.locks.ReentrantLock;

public class PackageServiceImpl implements PackageService {

    private final LockerService lockerService;
    private final PackageRepository packageRepository;
    private final LockerCodeGenerator lockerCodeGenerator;
    private final ReentrantLock assignmentLock = new ReentrantLock();

    public PackageServiceImpl(LockerService lockerService, PackageRepository packageRepository, LockerCodeGenerator lockerCodeGenerator) {
        this.lockerService = lockerService;
        this.packageRepository = packageRepository;
        this.lockerCodeGenerator = lockerCodeGenerator;
    }

    @Override
    public LockerResponse assignPackageToLocker(int packageId, PackageSize packageSize) {
        assignmentLock.lock();
        try {
            if(packageId <= 0 || packageSize == null) {
                throw new IllegalArgumentException("Invalid package ID or size.");
            }

            Locker locker = lockerService.getSuitableLockerId(packageSize);
            Package pkg = packageRepository.findById(packageId);

            if (locker == null || pkg == null) {
                throw new PackageAssignmentException("No suitable locker or package not found");
            }

            locker.setPackageId(packageId);
            String lockerCode = generateLockerCode();
            locker.setLockerCode(lockerCode);
//            locker.setAvailable(false);

            if (!lockerService.lockLocker(locker.getLockerId()) && !lockerService.updateLocker(locker)) {
                throw new PackageAssignmentException("Failed to assign package to locker");
            }

            return new LockerResponse(locker.getLockerId(),lockerCode);
        }finally {
            assignmentLock.unlock();
        }

    }

    @Override
    public Package retrievePackageFromLocker(int lockerId, String lockerCode) {
        assignmentLock.lock();
        try {
            Locker locker = lockerService.getLockerById(lockerId);
            if(!locker.isAvailable() && locker.getLockerCode().equals(lockerCode)) {
                // Locker is locked and the code matches
                Package pkg = packageRepository.findById(locker.getPackageId());
                if (pkg == null) {
                    throw new PackageNotFoundException("Package not found in locker.");
                }
                lockerService.unlockLocker(lockerId);
                pkg.setDelivered(true);
                packageRepository.update(pkg);
                return pkg;
            }
            throw new PackageRetrievalException("Locker is either available or the code is incorrect.");
        }finally {
            assignmentLock.unlock();
        }
    }

    @Override
    public boolean isPackageAssignedToLocker(int packageId) {
        return lockerService.checkPackageInLocker(packageId);
    }

    @Override
    public int returnPackageToLocker(int packageId) {
        Package pkg = packageRepository.findById(packageId);
        if(!pkg.isDelivered()){
            throw new PackageNotFoundException("Package is not delivered yet.");
        }
        LockerResponse lockerResponse = assignPackageToLocker(packageId, pkg.getPackageSize());
        pkg.setDelivered(false);
        packageRepository.update(pkg);
        return lockerResponse.lockerId();
    }

    private String generateLockerCode() {
        return lockerCodeGenerator.generateCode();
    }
}
