package repository;

import entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    void save(Item item);

    Optional<Item> findById(String id);

    List<Item> findAll();

    void deleteById(String id);
}
