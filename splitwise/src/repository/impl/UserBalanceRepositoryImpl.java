package repository.impl;

import entity.UserBalance;
import repository.UserBalanceRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserBalanceRepositoryImpl implements UserBalanceRepository {
    private final Map<String, UserBalance> userBalanceMap;

    public UserBalanceRepositoryImpl() {
        this.userBalanceMap = new ConcurrentHashMap<>();
    }

    @Override
    public void save(UserBalance userBalance) {
        userBalanceMap.put(userBalance.getUserId(), userBalance);
    }

    @Override
    public double findBalanceByUserId(String userId) {
        return userBalanceMap.getOrDefault(userId, new UserBalance(userId)).getBalance();
    }

    @Override
    public Optional<UserBalance> findByUserId(String userId){
        return Optional.ofNullable(userBalanceMap.get(userId));
    }
}
