package service.impl;

import entity.Auction;
import entity.Bid;
import entity.User;
import enums.AuctionState;
import exceptions.ItemNotExistException;
import repository.BidRepository;
import service.AuctionService;
import service.BidService;
import service.UserService;

import java.util.Objects;

public class BidServiceImpl implements BidService {
    private final BidRepository bidRepository;
    private final UserService userService;
    private final AuctionService auctionService;

    public BidServiceImpl(BidRepository bidRepository, UserService userService, AuctionService auctionService) {
        this.bidRepository = bidRepository;
        this.userService = userService;
        this.auctionService = auctionService;
    }

    @Override
    public String placeBid(String itemId, String userId, String auctionId, double bidAmount) {
        User user = userService.getUserById(userId);
        Auction auction = auctionService.getAuctionDetails(auctionId);
        if(auction.getOwner().getId().equals(userId)){
            throw new IllegalArgumentException("Auction owner cannot place a bid on their own auction.");
        }
        if(!Objects.equals(auction.getItem().getId(), itemId)){
            throw new IllegalArgumentException("Bid item does not match auction item.");
        }
        if (bidAmount <= auction.getStartingPrice()) {
            throw new IllegalArgumentException("Bid amount must be greater than the starting price.");
        }
        if (auction.getStatus() != AuctionState.ACTIVE) {
            throw new IllegalArgumentException("Cannot place a bid on an auction that is not ongoing.");
        }
        if(auction.getHighestBidAmount()<bidAmount){
            auctionService.updateAuctionHighestBid(auctionId, userId, bidAmount);
        }

        String bidId = generateBidId(auctionId, userId);
        Bid bid = new Bid(bidId, auction, user, bidAmount);
        bidRepository.save(bid);
        System.out.println("Bid placed successfully with ID: " + bidId);
        return bidId;
    }

    @Override
    public void updateBid(String bidId, double newBidAmount) {
        Bid existingBid = getBidDetails(bidId);
        Auction auction = auctionService.getAuctionDetails(existingBid.getAuction().getId());
        if (auction.getStatus() != AuctionState.ACTIVE) {
            throw new IllegalArgumentException("Cannot update a bid on an auction that is not active.");
        }
        if (newBidAmount <= auction.getStartingPrice()) {
            throw new IllegalArgumentException("New bid amount must be greater than the starting price.");
        }
        if (newBidAmount <= existingBid.getAmount()) {
            throw new IllegalArgumentException("New bid amount must be greater than the current bid amount.");
        }
        if(auction.getHighestBidAmount()<newBidAmount){
            auctionService.updateAuctionHighestBid(auction.getId(), existingBid.getUser().getId(), newBidAmount);
        }

        existingBid.setAmount(newBidAmount);
        bidRepository.save(existingBid);
        System.out.println("Bid updated successfully with ID: " + bidId);
    }

    @Override
    public void cancelBid(String bidId) {
        Bid existingBid = getBidDetails(bidId);
        bidRepository.deleteById(existingBid.getId());
        System.out.println("Bid with ID: " + bidId + " has been canceled.");
    }

    @Override
    public Bid getBidDetails(String bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new ItemNotExistException("Bid not found with ID: " + bidId));
    }

    @Override
    public Bid getHighestBidForAuction(String auctionId) {
        Auction auction = auctionService.getAuctionDetails(auctionId);
        if (auction.getStatus() != AuctionState.ACTIVE) {
            throw new IllegalArgumentException("Cannot retrieve highest bid for an auction that is not active.");
        }
        return bidRepository.findHighestBidByAuctionId(auctionId)
                .orElseThrow(() -> new ItemNotExistException("No bids found for auction with ID: " + auctionId));
    }

    private String generateBidId(String auctionId, String userId) {
        return auctionId + "-" + userId;
    }
}
