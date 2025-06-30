package service;

import entity.User;

public interface UserService {
    void register(User user);

    boolean login(String username, String password);

    User getUserByUsername(String username);

    boolean updateUser(User user);

    void deleteUser(String username);

    boolean isUsernameAvailable(String username);

    boolean isUserValid(String username, String password);
}
