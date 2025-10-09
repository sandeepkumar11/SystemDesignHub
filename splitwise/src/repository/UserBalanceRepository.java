package repository;

import entity.UserBalance;

import java.util.Optional;

public interface UserBalanceRepository {
    void save(UserBalance userBalance);

    double findBalanceByUserId(String userId);

    Optional<UserBalance> findByUserId(String userId);
}
