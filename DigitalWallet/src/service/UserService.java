package service;

import model.User;

import java.util.Optional;

public interface UserService {
    String register(User user);
    String login(String email, String password);
    Optional<User> findById(String userId);
}
