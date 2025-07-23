package repository;

import entity.Bid;

import java.util.List;
import java.util.Optional;

public interface BidRepository {
    void save(Bid bid);

    Optional<Bid> findById(String id);

    List<Bid> findAll();

    Optional<Bid> findHighestBidByAuctionId(String auctionId);

    void deleteById(String id);

    List<Bid> findByAuctionId(String auctionId);

    List<Bid> findByUserId(String userId);
}
