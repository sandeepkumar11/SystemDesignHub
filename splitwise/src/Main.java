import repository.ExpenseRepository;
import repository.GroupRepository;
import repository.UserBalanceRepository;
import repository.UserRepository;
import repository.impl.ExpenseRepositoryImpl;
import repository.impl.GroupRepositoryImpl;
import repository.impl.UserBalanceRepositoryImpl;
import repository.impl.UserRepositoryImpl;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, Split wise!");

        UserRepository userRepository = new UserRepositoryImpl();
        GroupRepository groupRepository = new GroupRepositoryImpl();
        ExpenseRepository expenseRepository = new ExpenseRepositoryImpl();
        UserBalanceRepository userBalanceRepository = new UserBalanceRepositoryImpl();
        new SplitWise(userRepository, groupRepository, expenseRepository, userBalanceRepository);
    }
}