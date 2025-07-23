package repository.impl;

import entity.User;
import repository.UserRepository;

import java.util.List;
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
    public Optional<User> findByEmail(String email) {
        return userStore.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password){
        return userStore.values().stream()
                .filter(user -> user.getEmail().equals(email) && user.getPasswordHash().equals(password))
                .findFirst();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userStore.get(id));
    }

    @Override
    public List<User> findAll() {
        return List.copyOf(userStore.values());
    }

    @Override
    public void deleteById(String id) {
        userStore.remove(id);
    }
}
