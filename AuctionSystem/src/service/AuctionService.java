package service;

import entity.Auction;
import model.AuctionDto;

import java.util.List;

public interface AuctionService {
    String createAuction(AuctionDto auction);

    void updateAuction(String auctionId, AuctionDto auction);

    void updateAuctionHighestBid(String auctionId, String userid, double currentPrice);

    void cancelAuction(String auctionId);

    Auction getAuctionDetails(String auctionId);

    List<Auction> getAllActiveAuctions();
}
