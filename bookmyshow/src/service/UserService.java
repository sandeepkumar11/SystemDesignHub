package service;

import dto.request.UserRequest;
import dto.response.UserResponse;

public interface UserService {
    String getUserEmail(String userId);

    UserResponse addUser(UserRequest request);

    UserResponse getUserById(String userId);

    UserResponse updateUser(String userId, UserRequest request);

    void deleteUser(String userId);
}
