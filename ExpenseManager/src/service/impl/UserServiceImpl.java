package service.impl;

import entity.User;
import repository.UserRepository;
import service.UserService;

import java.util.logging.Logger;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Logger logger = Logger.getLogger(UserServiceImpl.class.getName());

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            logger.warning("Registration failed: Username already exists.");
            return;
        }

        userRepository.save(user);
        logger.info("User registered successfully: " + user.getUsername());
    }

    @Override
    public boolean login(String username, String password) {
        if (userRepository.isUserValid(username, password)){
            logger.info("User logged in successfully: " + username);
            return true;
        }

        logger.warning("Login failed: Invalid username or password.");
        return false;

    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
    }

    @Override
    public boolean updateUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
            logger.info("User updated successfully: " + user.getUsername());
            return true;
        }

        logger.warning("Update failed: User does not exist.");
        return false;
    }

    @Override
    public void deleteUser(String username) {
        if (userRepository.existsByUsername(username)) {
            userRepository.deleteByUsername(username);
            logger.info("User deleted successfully: " + username);
            return;
        }

        logger.warning("Deletion failed: User does not exist.");
    }

    @Override
    public boolean isUsernameAvailable(String username) {
        return userRepository.isUsernameAvailable(username);
    }

    @Override
    public boolean isUserValid(String username, String password) {
        return userRepository.isUserValid(username, password);
    }
}
