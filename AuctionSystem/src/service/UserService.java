package service;

import entity.User;

public interface UserService {
    void register(String name, String email, String password);

    User login(String email, String password);

    User getUserById(String userId);

    User getUserByEmail(String email);

    void updatePassword(String email, String oldPassword, String newPassword);

    void deleteUser(String email);
}
