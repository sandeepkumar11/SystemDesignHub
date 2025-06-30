package repository;

import entity.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    void deleteByUsername(String username);

    void save(User user);

    Optional<User> findByUsernameAndPassword(String username, String password);

    boolean isUserValid(String username, String password);

    boolean isUsernameAvailable(String username);
}
