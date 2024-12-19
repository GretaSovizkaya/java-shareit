package item.repository;

import item.model.Item;

import java.util.Collection;
import java.util.Optional;

public interface ItemRepository {
    Item addNewItem(Item item);

    Item updateItem(long itemId, Item updItem);

    void deleteItem(long id);

    Optional<Item> findById(long id);

    Collection<Item> findByOwner(long ownerId);

    Collection<Item> findBySearch(String text);
}