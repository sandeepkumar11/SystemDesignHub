package service.impl;

import entity.User;
import exceptions.ItemExistException;
import exceptions.ItemNotExistException;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void register(String name, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ItemExistException("User already exists");
        }
        String hashedPassword = hash(password);
        String normalizedEmail = email.toLowerCase();
        String userId = generateUserId(normalizedEmail, name);
        User user = new User(userId, name, normalizedEmail, hashedPassword);
        userRepository.save(user);
        System.out.println("User registered successfully: " + user.getName());
    }

    @Override
    public User login(String email, String password) {
        String hashedPassword = hash(password);
        User user = userRepository.findByEmailAndPassword(
                        email.toLowerCase(), hashedPassword)
                .orElseThrow(() -> new ItemNotExistException("Invalid login credentials"));
        System.out.println("User logged in successfully: " + user.getName());
        return user;
    }

    @Override
    public User getUserById(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotExistException("User not found with ID: " + userId));
    }

    @Override
    public User getUserByEmail(String email){
        return userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new ItemNotExistException("User not found with email: " + email));
    }

    @Override
    public void updatePassword(String email, String oldPassword, String newPassword) {
        User user = getUserByEmail(email);

        if (!user.getPasswordHash().equals(hash(oldPassword))) {
            throw new ItemNotExistException("Old password is incorrect");
        }

        String newHashedPassword = hash(newPassword);
        user.setPasswordHash(newHashedPassword);
        userRepository.save(user);
        System.out.println("Password updated successfully for user: " + user.getName());
    }

    @Override
    public void deleteUser(String email) {
        User user = getUserByEmail(email);

        userRepository.deleteById(user.getId());
        System.out.println("User deleted successfully: " + user.getName());
    }

    private String hash(String password) {
        return Integer.toHexString(password.hashCode());
    }

    private String generateUserId(String email, String name) {
        // Add custom ID generation logic if needed
        return email.toLowerCase() + "-" + name;
    }
}
