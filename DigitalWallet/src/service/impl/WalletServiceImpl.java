package service.impl;

import exception.InsufficientBalanceException;
import exception.ItemNotExistException;
import model.Wallet;
import repository.UserRepository;
import repository.WalletRepository;
import service.UserService;
import service.WalletService;
// TODO: Handle concurrency issues, e.g., using synchronized blocks or locks
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final UserService userService;

    public WalletServiceImpl(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }

    @Override
    public void createWallet(String userId){
        if(userService.findById(userId).isEmpty()){
            throw new ItemNotExistException("User does not exist: " + userId);
        }
        if (walletRepository.findByUserId(userId).isPresent()) {
            throw new ItemNotExistException("Wallet already exists for user: " + userId);
        }

        Wallet wallet = new Wallet(userId);
        walletRepository.save(wallet);
        System.out.println("Wallet created for user: " + userId);
    }

    @Override
    public boolean creditWallet(String userId, double amount) {
        Wallet wallet = getWallet(userId);
        validateAmount(amount);

        wallet.addFunds(amount);
        walletRepository.update(wallet);
        System.out.println("Added " + amount + " to wallet for user: " + userId);
        return true;
    }

    @Override
    public boolean debitWallet(String userId, double amount) {
        Wallet wallet = getWallet(userId);
        validateAmount(amount);

        if (wallet.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient funds in wallet.");
        }

        wallet.deductFunds(amount);
        walletRepository.update(wallet);
        System.out.println("Deducted " + amount + " from wallet for user: " + userId);
        return true;
    }

    @Override
    public double getBalance(String userId) {
        return getWallet(userId).getBalance();
    }

    private Wallet getWallet(String userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new ItemNotExistException("Wallet not found for user: " + userId));
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }
}
