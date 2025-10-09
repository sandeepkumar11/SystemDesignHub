package service.impl;

import dto.request.UserRequest;
import dto.response.UserResponse;
import entity.User;
import exception.ItemExists;
import exception.ItemNotFound;
import repository.UserRepository;
import service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getUserEmail(String userId) {
        User user = getUserByIdInternal(userId);
        return user.getEmail();
    }

    @Override
    public UserResponse addUser(UserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ItemExists("User with email " + request.getEmail() + " already exists");
        }
        User user = new User(
                UUID.randomUUID().toString(),
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        userRepository.save(user);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserResponse getUserById(String userId) {
        User user = getUserByIdInternal(userId);
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    @Override
    public UserResponse updateUser(String userId, UserRequest request) {
        User existingUser = getUserByIdInternal(userId);

        if (!existingUser.getEmail().equals(request.getEmail()) && userRepository.existsByEmail(request.getEmail())) {
            throw new ItemExists("User with email " + request.getEmail() + " already exists");
        }

        User updatedUser = new User(
                existingUser.getId(),
                request.getName() != null ? request.getName() : existingUser.getName(),
                request.getEmail() != null ? request.getEmail() : existingUser.getEmail(),
                request.getPassword() != null ? request.getPassword() : existingUser.getPassword()
        );

        userRepository.save(updatedUser);
        return new UserResponse(updatedUser.getId(), updatedUser.getName(), updatedUser.getEmail());
    }

    @Override
    public void deleteUser(String userId) {
        getUserByIdInternal(userId); // Ensure user exists
        userRepository.delete(userId);
    }

    private User getUserByIdInternal(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFound("User not found"));
    }
}
