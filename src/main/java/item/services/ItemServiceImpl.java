package item.services;

import exceptions.NotFoundException;
import item.dto.ItemDto;
import item.mapper.ItemMapper;
import item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public ItemDto addNewItem(ItemDto newItemDto, long ownerId) {
        userRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Пользователь " + ownerId + "не найден"));
        return ItemMapper.toItemDto(itemRepository.addNewItem(ItemMapper.toItem(newItemDto, ownerId)));
    }

    @Override
    public ItemDto updateItem(long itemId, ItemDto itemUpd, long ownerId) {
        userRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Пользователь " + ownerId + "не найден"));
        itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item " + itemId + "не найден"));
        return ItemMapper.toItemDto(itemRepository.updateItem(itemId, ItemMapper.toItem(itemUpd, ownerId)));
    }

    @Override
    public void delete(long itemId) {
        itemRepository.deleteItem(itemId);
    }

    @Override
    public ItemDto findById(long itemId) {
        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new NotFoundException("Item " + itemId + "не найден"));
        return ItemMapper.toItemDto(item); // Return item after the first retrieval
    }

    @Override
    public Collection<ItemDto> findByOwner(long ownerId) {
        userRepository.findById(ownerId)
                .orElseThrow(() -> new NotFoundException("Пользователь " + ownerId + "не найден"));
        return itemRepository.findByOwner(ownerId)
                .stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    @Override
    public Collection<ItemDto> findBySearch(String text) {
        if (text == null || text.isBlank()) {
            return Collections.emptyList();
        }
        return itemRepository.findBySearch(text)
                .stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }
}