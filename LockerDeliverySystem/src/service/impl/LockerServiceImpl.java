package service.impl;

import enums.PackageSize;
import exception.LockerNotFoundException;
import model.Locker;
import repository.LockerRepository;
import service.LockerService;
import strategy.LockerSelectionStrategy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class LockerServiceImpl implements LockerService {

    private final LockerRepository lockerRepository;
    private final LockerSelectionStrategy lockerSelectionStrategy;
    private final Map<Integer, ReentrantLock> lockerLocks = new ConcurrentHashMap<>();

    public LockerServiceImpl(LockerRepository lockerRepository,LockerSelectionStrategy lockerSelectionStrategy) {
        this.lockerRepository = lockerRepository;
        this.lockerSelectionStrategy = lockerSelectionStrategy;
    }

    @Override
    public Locker getLockerById(int lockerId) {
        if(lockerId<0){
            throw new IllegalArgumentException("Locker ID must be a positive integer.");
        }
        Locker locker = lockerRepository.findLockerById(lockerId);
        if (locker == null) {
            throw new LockerNotFoundException("Locker not found with ID: " + lockerId);
        }
        return locker;
    }

    @Override
    public boolean lockLocker(int lockerId) {
        ReentrantLock lock = lockerLocks.computeIfAbsent(lockerId, k -> new ReentrantLock());
        lock.lock();
        try {
            System.out.println("Attempting to lock locker with ID: " + lockerId);
            Locker locker = getLockerById(lockerId);
            if (!locker.isAvailable()) {
                return false;
            }
            locker.setAvailable(false);
            System.out.println("Locker with ID: " + lockerId + " is now locked.");
            return lockerRepository.updateLocker(locker);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean unlockLocker(int lockerId) {
        ReentrantLock lock = lockerLocks.computeIfAbsent(lockerId, k -> new ReentrantLock());
        lock.lock();
        try{
            Locker locker = getLockerById(lockerId);
            locker.setAvailable(true);
            locker.setPackageId(-1);
            locker.setLockerCode(null);
            return lockerRepository.updateLocker(locker);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isLockerLocked(int lockerId) {
        return !getLockerById(lockerId).isAvailable();
    }

    @Override
    public Locker getSuitableLockerId(PackageSize packageSize) {

        if(packageSize==null){
            throw new IllegalArgumentException("Package size cannot be null.");
        }

        synchronized (this) {
            List<Locker> lockers = lockerRepository.getAllLockers();

            Locker locker = lockerSelectionStrategy.selectLocker(lockers, packageSize);
//            locker.setAvailable(false);
            lockerRepository.updateLocker(locker);
            return locker;
        }
    }

    @Override
    public boolean updateLocker(Locker locker) {
        if (locker == null || locker.getLockerId() <= 0) {
            throw new LockerNotFoundException("Invalid locker data provided.");
        }
        return lockerRepository.updateLocker(locker);
    }

    @Override
    public boolean checkPackageInLocker(int packageId) {
        if (packageId <= 0) {
            throw new IllegalArgumentException("Package ID must be positive");
        }
        return lockerRepository.getAllLockers().stream()
                .anyMatch(locker -> locker.getPackageId() == packageId);
    }
}
