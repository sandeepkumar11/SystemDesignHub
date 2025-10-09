import repository.UserRepository;
import repository.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Split wise!");

        UserRepository userRepository = new UserRepositoryImpl();
        new SplitWise(userRepository);
    }
}