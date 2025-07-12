package repository;

import model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void save(User user);

    Optional<User> findById(String userId);

    Optional<User> findByEmail(String email);

    void update(String userId, User user);

    void delete(String userId);

    boolean userExists(String userId);
}
