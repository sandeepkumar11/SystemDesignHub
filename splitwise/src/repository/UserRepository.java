package repository;

import entity.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(String userId);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    void delete(String userId);
}
