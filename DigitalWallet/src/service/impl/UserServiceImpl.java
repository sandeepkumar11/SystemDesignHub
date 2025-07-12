package service.impl;

import exception.DuplicateEntryException;
import exception.InvalidCredentialException;
import model.User;
import repository.UserRepository;
import service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String register(User user) {
        if (!validateUser(user)) {
            System.err.println("User validation failed for: " + user.getEmail());
        }
        userRepository.save(user);
        System.out.println("User registered successfully: " + user.getEmail());
        return user.getUserId();
    }

    @Override
    public String login(String email, String password) {
        if (email == null || password == null) {
            throw new DuplicateEntryException("Email and password cannot be null.");
        }

        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password))
                .map(User::getName).orElseThrow(() -> new InvalidCredentialException("Invalid email or password."));
    }

    @Override
    public Optional<User> findById(String userId){
        if (userId == null) {
            throw new DuplicateEntryException("User ID cannot be null.");
        }

        return userRepository.findById(userId);
    }

    private boolean validateUser(User user) {
        if( user == null || user.getEmail() == null || user.getPassword() == null) {
            throw new DuplicateEntryException("All fields are required for user registration.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateEntryException("User with email " + user.getEmail() + " already exists.");
        }

        return true;
    }
}
