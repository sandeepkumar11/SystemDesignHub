package service;

import entity.Item;

public interface ItemService {
    String addItem(String name, String description, double price);

    void updateItem(String itemId, Item item);

    void deleteItem(String itemId);

    Item getItemById(String itemId);
}
