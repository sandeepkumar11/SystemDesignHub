import enums.LockerSize;
import enums.PackageSize;
import model.Locker;
import model.LockerResponse;
import model.Package;
import repository.LockerRepository;
import repository.PackageRepository;
import repository.impl.LockerRepositoryImpl;
import repository.impl.PackageRepositoryImpl;
import service.LockerService;
import service.PackageService;
import service.impl.LockerServiceImpl;
import service.impl.PackageServiceImpl;
import strategy.LockerCodeGenerator;
import strategy.LockerSelectionStrategy;
import strategy.impl.SmallestFitLockerSelectionStrategy;
import strategy.impl.UUIDLockerCodeGenerator;

public class LockerManager {
    void demo() {
        // Initialize repositories
        LockerRepository lockerRepository = new LockerRepositoryImpl();
        PackageRepository packageRepository = new PackageRepositoryImpl();
        // Create lockers for demo
        Locker locker1 = new Locker(1, LockerSize.SMALL);
        Locker locker2 = new Locker(2, LockerSize.MEDIUM);
        Locker locker3 = new Locker(3, LockerSize.LARGE);
        Locker locker4 = new Locker(4, LockerSize.MEDIUM);
        Locker locker5 = new Locker(5, LockerSize.SMALL);

        // Save lockers to repository
        lockerRepository.saveLocker(locker1);
        lockerRepository.saveLocker(locker2);
        lockerRepository.saveLocker(locker3);
        lockerRepository.saveLocker(locker4);
        lockerRepository.saveLocker(locker5);

        Package package1 = new Package(1, PackageSize.SMALL);
        Package package2 = new Package(2, PackageSize.MEDIUM);
        Package package3 = new Package(3, PackageSize.LARGE);
        Package package4 = new Package(4, PackageSize.MEDIUM);
        Package package5 = new Package(5, PackageSize.SMALL);

        // Save packages to repository
        packageRepository.save(package1);
        packageRepository.save(package2);
        packageRepository.save(package3);
        packageRepository.save(package4);
        packageRepository.save(package5);


        LockerSelectionStrategy lockerSelectionStrategy = new SmallestFitLockerSelectionStrategy();
        LockerCodeGenerator lockerCodeGenerator = new UUIDLockerCodeGenerator();
        LockerService lockerService = new LockerServiceImpl(lockerRepository,lockerSelectionStrategy);
        PackageService packageService = new PackageServiceImpl(lockerService, packageRepository,lockerCodeGenerator);

        // Assign packages to lockers
        try {
            LockerResponse lockerResponse1 = packageService.assignPackageToLocker(
                    package1.getPackageId(), package1.getPackageSize());
            System.out.println("Package " + package1.getPackageId() +
                    " assigned to locker " + lockerResponse1.lockerId() +
                    " with code " + lockerResponse1.lockerCode());

            Package retrievedPackage1 = packageService.retrievePackageFromLocker(
                    lockerResponse1.lockerId(), lockerResponse1.lockerCode());

            System.out.println("Package " + retrievedPackage1.getPackageId() +
                    " retrieved from locker " + lockerResponse1.lockerId());

            int returnedLockerId = packageService.returnPackageToLocker(package1.getPackageId());
            System.out.println("Package " + package1.getPackageId() +
                    " returned to locker " + returnedLockerId);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
}
