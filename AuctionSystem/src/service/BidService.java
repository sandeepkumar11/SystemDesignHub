package service;

import entity.Bid;

public interface BidService {
    String placeBid(String itemId, String userId, String auctionId, double bidAmount);

    void updateBid(String bidId, double newBidAmount);

    void cancelBid(String bidId);

    Bid getBidDetails(String bidId);

    Bid getHighestBidForAuction(String auctionId);
}
