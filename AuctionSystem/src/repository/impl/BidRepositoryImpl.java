package repository.impl;

import entity.Bid;
import repository.BidRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class BidRepositoryImpl implements BidRepository {
    private final Map<String, Bid> bidStorage;

    public BidRepositoryImpl(){
        this.bidStorage = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Bid bid) {
        bidStorage.put(bid.getId(), bid);
    }

    @Override
    public Optional<Bid> findById(String id) {
        return Optional.ofNullable(bidStorage.get(id));
    }

    @Override
    public List<Bid> findAll() {
        return List.copyOf(bidStorage.values());
    }

    @Override
    public Optional<Bid> findHighestBidByAuctionId(String auctionId){
        return bidStorage.values().stream()
                .filter(bid -> bid.getAuction().getId().equals(auctionId))
                .max(Comparator.comparingDouble(Bid::getAmount));
    }

    @Override
    public void deleteById(String id) {
        bidStorage.remove(id);
    }

    @Override
    public List<Bid> findByAuctionId(String auctionId) {
        return bidStorage.values().stream()
                .filter(bid -> bid.getAuction().getId().equals(auctionId))
                .toList();
    }

    @Override
    public List<Bid> findByUserId(String userId) {
        return bidStorage.values().stream()
                .filter(bid -> bid.getUser().getId().equals(userId))
                .toList();
    }
}
