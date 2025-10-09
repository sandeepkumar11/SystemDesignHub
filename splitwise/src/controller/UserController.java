package controller;

import dto.request.UserRequest;
import dto.response.UserResponse;
import repository.UserRepository;
import service.UserService;
import service.impl.UserServiceImpl;

public class UserController {
    private final UserService userService;

    public UserController(UserRepository userRepository) {
        // It's for demonstration.
        this.userService = new UserServiceImpl(userRepository);
    }

    public String getUserById(String userId) {
        UserResponse response = userService.getUserById(userId);
        return "User found: " + response.getName() + " (" + response.getEmail() + ")";
    }

    public String getUserEmail(String userId) {
        String email = userService.getUserEmail(userId);
        return "User email: " + email;
    }

    public String addUser(UserRequest request) {
        UserResponse response = userService.addUser(request);
        return "User added successfully with ID: " + response.getId();
    }

    public String updateUser(String userId, UserRequest request) {
        UserResponse response = userService.updateUser(userId, request);
        return "User updated successfully: " + response.getName() + " (" + response.getEmail() + ")";
    }

    public String deleteUser(String userId) {
        userService.deleteUser(userId);
        return "User deleted successfully";
    }
}
