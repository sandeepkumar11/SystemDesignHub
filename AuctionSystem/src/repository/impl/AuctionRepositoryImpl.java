package repository.impl;

import entity.Auction;
import repository.AuctionRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class AuctionRepositoryImpl implements AuctionRepository {
    private final Map<String, Auction> auctionStorage;

    public AuctionRepositoryImpl() {
        this.auctionStorage = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Auction auction) {
        auctionStorage.put(auction.getId(), auction);
    }

    @Override
    public Optional<Auction> findById(String id) {
        return Optional.ofNullable(auctionStorage.get(id));
    }

    @Override
    public List<Auction> findAll() {
        return List.copyOf(auctionStorage.values());
    }

    @Override
    public void deleteById(String id) {
        auctionStorage.remove(id);
    }

    @Override
    public List<Auction> findByItemId(String itemId) {
        return auctionStorage.values().stream()
                .filter(auction -> auction.getItem().getId().equals(itemId))
                .toList();
    }

    @Override
    public List<Auction> findByUserId(String userId) {
        return auctionStorage.values().stream()
                .filter(auction -> auction.getOwner().getId().equals(userId))
                .toList();
    }

    @Override
    public List<Auction> findActiveAuctions() {
        return auctionStorage.values().stream()
                .filter(auction -> auction.getStatus().isActive())
                .sorted(Comparator.comparing(Auction::getEndTime))
                .toList();
    }

    @Override
    public boolean existsByItemIdAndUserId(String itemId, String userId){
        return auctionStorage.values().stream()
                .anyMatch(auction -> auction.getItem().getId().equals(itemId)
                        && auction.getOwner().getId().equals(userId));
    }
}
