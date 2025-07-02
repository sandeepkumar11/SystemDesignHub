package strategy;

import enums.PackageSize;
import model.Locker;

import java.util.List;

public interface LockerSelectionStrategy {
    Locker selectLocker(List<Locker> availableLockers, PackageSize packageSize);
}
