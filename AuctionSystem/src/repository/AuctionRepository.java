package repository;

import entity.Auction;

import java.util.List;
import java.util.Optional;

public interface AuctionRepository {
    void save(Auction auction);

    Optional<Auction> findById(String id);

    List<Auction> findAll();

    void deleteById(String id);

    List<Auction> findByItemId(String itemId);

    List<Auction> findByUserId(String userId);

    List<Auction> findActiveAuctions();

    boolean existsByItemIdAndUserId(String itemId, String userId);
}
