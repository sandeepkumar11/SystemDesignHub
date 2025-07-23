import entity.Item;
import model.AuctionDto;
import repository.AuctionRepository;
import repository.ItemRepository;
import repository.UserRepository;
import repository.impl.AuctionRepositoryImpl;
import repository.impl.ItemRepositoryImpl;
import repository.impl.UserRepositoryImpl;
import service.AuctionService;
import service.ItemService;
import service.UserService;
import service.impl.AuctionServiceImpl;
import service.impl.ItemServiceImpl;
import service.impl.UserServiceImpl;

import java.time.LocalDateTime;

public class AuctionSystem {
    public void start() {
        System.out.println("Auction System started.");
        printSeparator();

        // Initialize services, repositories, and other components here
        UserRepository userRepository = new UserRepositoryImpl();
        UserService userService = new UserServiceImpl(userRepository);
        userOperations(userService);

        ItemRepository itemRepository = new ItemRepositoryImpl();
        ItemService itemService = new ItemServiceImpl(itemRepository);
        itemOperations(itemService);

        AuctionRepository auctionRepository = new AuctionRepositoryImpl();
        AuctionService auctionService = new AuctionServiceImpl(auctionRepository, userService, itemService);
        auctionOperations(auctionService);

    }
    private void userOperations(UserService userService) {
        userService.register("Alice", "alice@email.com", "password123");
        userService.register("Bob", "bob@email.com", "password456");
        printInternalSeparator();
        try {
            userService.login("Bob@email.com", "password456");
            printSeparator();
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
        }
    }
    private void itemOperations(ItemService itemService){
        String id1 = itemService.addItem("Laptop", "High-end gaming laptop", 1500.00);
        String id2 = itemService.addItem("Smartphone", "Latest model smartphone", 800.00);
        String id3 = itemService.addItem("Headphones", "Noise-cancelling headphones", 200.00);
        printInternalSeparator();
        try {
            Item item = itemService.getItemById(id2);
            System.out.println("Retrieved Item: " + item.getName() + ", Price: " + item.getPrice());
            printSeparator();
        } catch (Exception e) {
            System.out.println("Item retrieval failed: " + e.getMessage());
        }
    }
    private void auctionOperations(AuctionService auctionService) {
        AuctionDto auctionDto1 = new AuctionDto.Builder()
                .setItemId("item-Laptop")
                .setUserId("alice@email.com-Alice")
                .setStartingPrice(1500.00)
                .setStartTime(LocalDateTime.now().plusDays(1))
                .setEndTime(LocalDateTime.now().plusDays(2))
                .setDescription("Auction for Laptop")
                .build();
        AuctionDto auctionDto2 = new AuctionDto.Builder()
                .setItemId("item-Headphones")
                .setUserId("bob@email.com-Bob")
                .setStartingPrice(200.00)
                .setStartTime(LocalDateTime.now().plusDays(1))
                .setEndTime(LocalDateTime.now().plusDays(2))
                .setDescription("Auction for Headphones")
                .build();
        String auction1Id = auctionService.createAuction(auctionDto1);
        String auction2Id = auctionService.createAuction(auctionDto2);
        printInternalSeparator();
    }
    private void printSeparator() {
        System.out.println("#".repeat(60));
    }
    private void printInternalSeparator() {
        System.out.println("-".repeat(60));
    }
}
