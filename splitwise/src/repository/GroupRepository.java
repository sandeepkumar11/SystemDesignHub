package repository;

import entity.Group;

import java.util.Optional;

public interface GroupRepository {
    void save(Group group);

    Optional<Group> findById(String id);

    boolean existsById(String id);

    void delete(String id);
}
