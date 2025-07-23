package repository.impl;

import entity.Item;
import repository.ItemRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ItemRepositoryImpl implements ItemRepository {
    private final Map<String, Item> itemStore;

    public ItemRepositoryImpl(){
        this.itemStore = new ConcurrentHashMap<>();
    }

    @Override
    public void save(Item item) {
        itemStore.put(item.getId(),item);
    }

    @Override
    public Optional<Item> findById(String id) {
        return Optional.ofNullable(itemStore.get(id));
    }

    @Override
    public List<Item> findAll() {
        return List.copyOf(itemStore.values());
    }

    @Override
    public void deleteById(String id) {
        itemStore.remove(id);
    }
}
