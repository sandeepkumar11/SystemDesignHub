import entity.User;
import repository.UserRepository;
import repository.impl.UserRepositoryImpl;
import service.UserService;
import service.impl.UserServiceImpl;

public class ExpenseManager {
    void demo() {

        // Repositories initialization
        UserRepository userRepository = new UserRepositoryImpl();

        // UserService initialization
        UserService userService = new UserServiceImpl(userRepository);
        User user1 = new User("SandeepKumar", "password123", "sandeep@gmail.com");
        User user2 = new User("JohnDoe", "john123", "john@gmail.com");
        User user3 = new User("JaneDoe", "jane123", "jone@gmail.com");
        User user4 = new User("AliceSmith", "alice123", "alicesmith@gmail.com");
        userService.register(user1);
        userService.register(user2);
        userService.register(user3);
        userService.register(user4);
        userService.deleteUser("JaneDoe");

    }
}
