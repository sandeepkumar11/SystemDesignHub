package service.impl;

import entity.Auction;
import entity.Item;
import entity.User;
import enums.AuctionState;
import model.AuctionDto;
import repository.AuctionRepository;
import service.AuctionService;
import service.ItemService;
import service.UserService;

import java.util.List;

public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final UserService userService;
    private final ItemService itemService;

    public AuctionServiceImpl(AuctionRepository auctionRepository, UserService userService, ItemService itemService) {
        this.auctionRepository = auctionRepository;
        this.userService = userService;
        this.itemService = itemService;
    }

    @Override
    public String createAuction(AuctionDto auction) {
        validateAuction(auction);
        String auctionId = generateAuctionId(auction.getItemId(), auction.getUserId());
        User user = userService.getUserById(auction.getUserId());
        Item item = itemService.getItemById(auction.getItemId());

        Auction newAuction = new Auction.Builder()
                .setId(auctionId)
                .setItem(item)
                .setOwner(user)
                .setStartingPrice(auction.getStartingPrice())
                .setStatus(AuctionState.NOT_STARTED)
                .setStartTime(auction.getStartTime())
                .setEndTime(auction.getEndTime())
                .setDescription(auction.getDescription())
                .build();

        auctionRepository.save(newAuction);
        System.out.println("Auction created with ID: " + auctionId);
        return auctionId;
    }

    @Override
    public void updateAuction(String auctionId, AuctionDto auction) {
        validateAuction(auction);
        Auction existingAuction = getAuctionDetails(auctionId);
        if (existingAuction.getStatus() != AuctionState.NOT_STARTED) {
            throw new IllegalArgumentException("Cannot update an auction that has already started or ended");
        }
        // Update the auction details
    }

    @Override
    public void updateAuctionHighestBid(String auctionId, String userId, double currentPrice){
        Auction auction = getAuctionDetails(auctionId);
        if (auction.getStatus() != AuctionState.ACTIVE) {
            throw new IllegalArgumentException("Cannot update current price of an auction that is not active");
        }
        if (currentPrice <= auction.getHighestBidAmount()) {
            throw new IllegalArgumentException("Current price must be greater than the starting price");
        }
        User user = userService.getUserById(userId);
        auction.setOwner(user);
        auction.setHighestBidAmount(currentPrice);
        auctionRepository.save(auction);
        System.out.println("Auction with ID: " + auctionId + " current price updated to: " + currentPrice);
    }

    @Override
    public void cancelAuction(String auctionId) {
        Auction auction = getAuctionDetails(auctionId);
        if (auction.getStatus() == AuctionState.ENDED || auction.getStatus() == AuctionState.CANCELLED) {
            throw new IllegalArgumentException("Cannot cancel an auction that has already ended or been canceled");
        }
        auction.setStatus(AuctionState.CANCELLED);
        auctionRepository.save(auction);
        System.out.println("Auction with ID: " + auctionId + " has been canceled.");
    }

    @Override
    public Auction getAuctionDetails(String auctionId) {
        return auctionRepository.findById(auctionId).orElseThrow(
                () -> new IllegalArgumentException("Auction not found with ID: " + auctionId));
    }

    @Override
    public List<Auction> getAllActiveAuctions() {
        return auctionRepository.findActiveAuctions();
    }

    private String generateAuctionId(String itemId, String userId) {
        return itemId + "-" + userId + "-" + System.currentTimeMillis();
    }

    private void validateAuction(AuctionDto auction) {
        if (auction.getStartingPrice() <= 0) {
            throw new IllegalArgumentException("Starting price must be greater than zero");
        }
        if (auction.getEndTime().isBefore(auction.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }
        if (auctionRepository.existsByItemIdAndUserId(auction.getItemId(), auction.getUserId())) {
            throw new IllegalArgumentException("Auction for this item by this user already exists");
        }
    }
}
