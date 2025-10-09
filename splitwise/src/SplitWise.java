import controller.ExpenseController;
import controller.GroupController;
import controller.UserBalanceController;
import controller.UserController;
import dto.request.ExpenseRequest;
import dto.request.UserRequest;
import dto.response.ExpenseResponse;
import dto.response.GroupResponse;
import repository.ExpenseRepository;
import repository.GroupRepository;
import repository.UserBalanceRepository;
import repository.UserRepository;
import service.UserBalanceService;
import service.impl.UserBalanceServiceImpl;

import java.util.List;

public class SplitWise {

    public SplitWise(UserRepository userRepository, GroupRepository groupRepository,
                     ExpenseRepository expenseRepository, UserBalanceRepository userBalanceRepository) {
        demoUserService(userRepository);
        System.out.println("#".repeat(50));
        demoGroupService(groupRepository);
        System.out.println("#".repeat(50));
        UserBalanceService userBalanceService = new UserBalanceServiceImpl(userBalanceRepository);
        demoUserBalanceService(userBalanceService);
        System.out.println("#".repeat(50));
        demoExpenseService(expenseRepository, userBalanceService);
    }

    private void demoUserService(UserRepository userRepository) {
        UserController userController = new UserController(userRepository);

        // Add a new user
        String addUserResponse = userController.addUser(new UserRequest("John Doe", "john@email.com", "password123"));
        System.out.println(addUserResponse);
        addUserResponse = userController.addUser(new UserRequest("Jane Doe", "jane@email.com", "password123"));
        System.out.println(addUserResponse);
    }

    private void demoGroupService(GroupRepository groupRepository) {
        GroupController groupController = new GroupController(groupRepository);

        // Create a new group
        GroupResponse createGroupResponse = groupController.create(new dto.request.GroupRequest("Friends", "Group for friends"));
        System.out.println(createGroupResponse);

        // Add a member to the group
        String addMemberResponse = groupController.addMember(createGroupResponse.getId(), "user-id-123");
        System.out.println(addMemberResponse);
        addMemberResponse = groupController.addMember(createGroupResponse.getId(), "user-id-456");
        System.out.println(addMemberResponse);

        groupController.getGroup(createGroupResponse.getId());
    }

    private void demoUserBalanceService(UserBalanceService userBalanceService) {

        UserBalanceController userBalanceController = new UserBalanceController(userBalanceService);

        // Add balance to user
        String addBalanceResponse = userBalanceController.addBalance("user-id-123", 100.0);
        System.out.println(addBalanceResponse);
        // Get user balance
        String getBalanceResponse = userBalanceController.getBalance("user-id-123");
        System.out.println(getBalanceResponse);
        // Deduct balance from user
        String deductBalanceResponse = userBalanceController.deductBalance("user-id-123", 50.0);
        System.out.println(deductBalanceResponse);
        // Get user balance again
        getBalanceResponse = userBalanceController.getBalance("user-id-123");
        System.out.println(getBalanceResponse);
    }

    private void demoExpenseService(ExpenseRepository expenseRepository, UserBalanceService userBalanceService) {
        ExpenseController expenseController = new ExpenseController(expenseRepository, userBalanceService);
        ExpenseResponse response = expenseController.addExpense(new ExpenseRequest("Coffee", 20.0, "user-id-123", List.of("user-id-123","user-id-456")));
        System.out.println("Added Expense: " + response);

        ExpenseResponse expenseResponse = expenseController.getExpenseById(response.getId());
        System.out.println("Fetched Expense: " + expenseResponse);
        String deleteResponse = expenseController.deleteExpense(expenseResponse.getId());
        System.out.println(deleteResponse);

//        expenseController.getExpenseById(expenseResponse.getId());
    }
}
