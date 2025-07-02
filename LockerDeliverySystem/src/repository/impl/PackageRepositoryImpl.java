package repository.impl;

import model.Package;
import repository.PackageRepository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PackageRepositoryImpl implements PackageRepository {
    private final Map<Integer, Package> packageMap;

    public PackageRepositoryImpl() {
        this.packageMap = new ConcurrentHashMap<>();
    }

    @Override
    public Package findById(int id) {
        return packageMap.getOrDefault(id, null);
    }

    @Override
    public void save(Package pkg) {
        if (pkg != null && pkg.getPackageId() != -1) {
            packageMap.put(pkg.getPackageId(), pkg);
        }
    }

    @Override
    public void update(Package pkg) {
        if (pkg != null && pkg.getPackageId() != -1) {
            packageMap.put(pkg.getPackageId(), pkg);
        }
    }

    @Override
    public void delete(int id) {
        packageMap.remove(id);
    }

    @Override
    public List<Package> findAll() {
        return List.copyOf(packageMap.values());
    }
}
