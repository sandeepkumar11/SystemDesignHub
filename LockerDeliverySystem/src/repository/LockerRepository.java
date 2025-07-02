package repository;

import model.Locker;

import java.util.List;

public interface LockerRepository {
    Locker findLockerById(int lockerId);

    boolean saveLocker(Locker locker);

    boolean updateLocker(Locker locker);

    boolean deleteLocker(int lockerId);

    List<Locker> getAllLockers();
}
