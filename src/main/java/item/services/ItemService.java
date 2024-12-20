package item.services;

import item.dto.ItemDto;
import org.springframework.stereotype.Service;

import java.util.Collection;


/*@Service
public interface ItemService {
    ItemDto addNewItem(ItemDto item, long ownerId);

    ItemDto updateItem(long id, ItemDto item, long userId);

    void delete(long id);

    ItemDto findById(long id);

    Collection<ItemDto> findByOwner(long ownerId);

    Collection<ItemDto> findBySearch(String text);
}