import controller.UserController;
import dto.request.UserRequest;
import repository.UserRepository;

public class SplitWise {

    public SplitWise(UserRepository userRepository) {
        demoUserService(userRepository);
    }

    private void demoUserService(UserRepository userRepository) {
        UserController userController = new UserController(userRepository);

        // Add a new user
        String addUserResponse = userController.addUser(new UserRequest("John Doe", "john@email.com", "password123"));
        System.out.println(addUserResponse);
        addUserResponse = userController.addUser(new UserRequest("Jane Doe", "jane@email.com", "password123"));
        System.out.println(addUserResponse);
    }
}
