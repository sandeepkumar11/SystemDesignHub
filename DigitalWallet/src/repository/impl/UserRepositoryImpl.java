package repository.impl;

import model.User;
import repository.UserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepositoryImpl implements UserRepository {
    private final Map<String, User> userDatabase; // userId -> User mapping

    public UserRepositoryImpl(){
        this.userDatabase = new ConcurrentHashMap<>();
    }

    @Override
    public void save(User user) {
        userDatabase.put(user.getUserId(), user);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(userDatabase.get(userId));
    }

    @Override
    public Optional<User> findByEmail(String email){
        return userDatabase.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public void update(String userId, User user) {
        userDatabase.put(userId,user);
    }

    @Override
    public void delete(String userId) {
        userDatabase.remove(userId);
    }

    @Override
    public boolean userExists(String userId) {
        return userDatabase.containsKey(userId);
    }
}
