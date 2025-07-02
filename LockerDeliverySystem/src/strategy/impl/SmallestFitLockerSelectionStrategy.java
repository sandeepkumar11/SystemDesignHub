package strategy.impl;

import enums.PackageSize;
import exception.NoSuitableLockerException;
import model.Locker;
import strategy.LockerSelectionStrategy;

import java.util.Comparator;
import java.util.List;

public class SmallestFitLockerSelectionStrategy implements LockerSelectionStrategy {
    @Override
    public Locker selectLocker(List<Locker> availableLockers, PackageSize packageSize) {
        return availableLockers.stream()
                .filter(Locker::isAvailable)
                .filter(locker -> locker.getLockerSize().ordinal() >= packageSize.ordinal())
                .sorted(Comparator.comparing(Locker::getLockerSize))
                .findFirst()
                .orElseThrow(() -> new NoSuitableLockerException("No suitable locker found"));
    }
}
