import model.Transaction;
import model.User;
import repository.TransactionRepository;
import repository.TransferRepository;
import repository.UserRepository;
import repository.WalletRepository;
import repository.impl.TransactionRepositoryImpl;
import repository.impl.TransferRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import repository.impl.WalletRepositoryImpl;
import service.TransactionService;
import service.UserService;
import service.WalletService;
import service.impl.TransactionServiceImpl;
import service.impl.UserServiceImpl;
import service.impl.WalletServiceImpl;

public class DigitalWalletPayment {
    public void demo(){
        System.out.println("Digital Wallet Payment Demo");

        System.out.println("#".repeat(75));
        // Authentication repository and service
        UserRepository userRepository = new UserRepositoryImpl();
        WalletRepository walletRepository = new WalletRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        WalletService walletService = new WalletServiceImpl(walletRepository, userService);
        User user1 = new User("user1", "user1@gmail.com", "password1");
        String userId1 = "";
        User user2 = new User("user2", "user2@gmail.com", "password2");
        User user3 = new User("user3", "user3@gmail.com", "password3");
        User user4 = new User("user4", "user4@gmail.com", "password4");
        String userId4 = "";
        try{
            // Register users
            userId1 = userService.register(user1);
            walletService.createWallet(userId1);
            walletService.creditWallet(userId1,200);
//            String userId2 = userService.register(user2);
//            walletService.createWallet(userId2);
//            String userId3 = userService.register(user3);
//            walletService.createWallet(userId3);
            userId4 = userService.register(user4);
            walletService.createWallet(userId4);
            walletService.creditWallet(userId4,1000);

            // Login users
            System.out.println("Login User 1: " + userService.login("user1@gmail.com", "password1"));
//            userService.register(new User("user5", "user4@gmail.com", "password5")); // This should throw an exception due to duplicate email
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("#".repeat(75));

        // Transaction repository and service
        TransactionRepository<Transaction> transactionRepository = new TransactionRepositoryImpl();
        TransferRepository transferRepository = new TransferRepositoryImpl();
        TransactionService transactionService = new TransactionServiceImpl(
                transactionRepository, transferRepository, walletService, userService
        );

        try {
            String transactionId1 = transactionService.transferFunds(userId1,userId4,50);
            System.out.println(transactionService.getTransactionDetails(userId1, transactionId1));
        } catch (RuntimeException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("#".repeat(75));

    }
}
