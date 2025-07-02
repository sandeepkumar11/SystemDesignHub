package repository.impl;

import model.Locker;
import repository.LockerRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LockerRepositoryImpl implements LockerRepository {

    Map<Integer, Locker> lockers;

    public LockerRepositoryImpl() {
        this.lockers = new ConcurrentHashMap<>();
    }

    @Override
    public Locker findLockerById(int lockerId) {
        return lockers.getOrDefault(lockerId, null);
    }

    @Override
    public boolean saveLocker(Locker locker) {
        if (locker == null || lockers.containsKey(locker.getLockerId())) {
            return false;
        }
        lockers.put(locker.getLockerId(), locker);
        return true;
    }

    @Override
    public boolean updateLocker(Locker locker) {
        if (locker == null || !lockers.containsKey(locker.getLockerId())) {
            return false;
        }
        lockers.put(locker.getLockerId(), locker);
        return true;
    }

    @Override
    public boolean deleteLocker(int lockerId) {
        if (!lockers.containsKey(lockerId)) {
            return false;
        }
        lockers.remove(lockerId);
        return true;
    }

    @Override
    public List<Locker> getAllLockers() {
        return List.copyOf(lockers.values());
    }
}
