package repository.impl;

import entity.User;
import repository.UserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userStore;

    public UserRepositoryImpl() {
        this.userStore = new ConcurrentHashMap<>();
    }

    @Override
    public void save(User user) {
        userStore.put(user.getId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(userStore.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userStore.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void delete(String userId) {
        userStore.remove(userId);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userStore.values().stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }
}
