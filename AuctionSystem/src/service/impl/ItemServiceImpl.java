package service.impl;

import entity.Item;
import exceptions.ItemNotExistException;
import repository.ItemRepository;
import service.ItemService;

public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public String addItem(String name, String description, double price) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Item name cannot be empty");
        }
        String id = generateItemId(name);
        Item item = new Item(id, name, description, price);
        itemRepository.save(item);
        System.out.println("Item added with ID: " + id);
        return item.getId();
    }

    @Override
    public void updateItem(String itemId, Item item) {
        Item existingItem = getItemById(itemId);
        existingItem.setName(item.getName()!=null ? item.getName() : existingItem.getName());
        existingItem.setDescription(item.getDescription()!=null ? item.getDescription() : existingItem.getDescription());
        existingItem.setPrice(item.getPrice() > 0 ? item.getPrice() : existingItem.getPrice());
        itemRepository.save(existingItem);
        System.out.println("Item updated with ID: " + itemId);
    }

    @Override
    public void deleteItem(String itemId) {
        Item item = getItemById(itemId);
        itemRepository.deleteById(itemId);
        System.out.println("Item deleted with ID: " + itemId);
    }

    @Override
    public Item getItemById(String itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotExistException("Item does not exist with ID: " + itemId));
    }

    private String generateItemId(String name) {
        // Logic to generate a unique item ID
        return "item-" + name;
    }
}
