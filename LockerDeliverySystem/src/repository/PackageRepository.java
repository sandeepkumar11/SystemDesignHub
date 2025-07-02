package repository;

import model.Package;

import java.util.List;

public interface PackageRepository {

    Package findById(int id);

    void save(Package pkg);

    void update(Package pkg);

    void delete(int id);

    List<Package> findAll();
}
