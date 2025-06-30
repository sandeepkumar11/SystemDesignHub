package repository.impl;

import entity.User;
import repository.UserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository {

    Map<String, User> userDatabase;

    public UserRepositoryImpl(){
        userDatabase = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(userDatabase.get(username));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userDatabase.containsKey(username);
    }

    @Override
    public void deleteByUsername(String username) {
        userDatabase.remove(username);
    }

    @Override
    public void save(User user) {
        userDatabase.put(user.getUsername(), user);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userDatabase.values().stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }

    @Override
    public boolean isUserValid(String username, String password) {
        return userDatabase.values().stream()
                .anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return !userDatabase.containsKey(username);
    }
}
