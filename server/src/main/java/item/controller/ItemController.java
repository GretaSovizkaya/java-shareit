package item.controller;

import item.dto.CommentDto;
import item.dto.CommentInfoDto;
import item.dto.ItemDto;
import item.services.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    private final ItemService service;
    static final String userParmHeader = "X-Sharer-User-Id";

    @GetMapping("/{id}")
    public ItemDto get(@RequestHeader(userParmHeader) Long userId, @PathVariable Long id) {
        log.info("==>Получение Item по id: {}", id);
        ItemDto itemDto = service.findById(id, userId);
        return itemDto;
    }

    @GetMapping
    public Collection<ItemDto> getByOwnerId(@RequestHeader(userParmHeader) Long userId) {
        log.info("==>Получение Item по Владельцу: {}", userId);
        Collection<ItemDto> itemsByOwner = service.findByOwner(userId);
        return itemsByOwner;
    }

    @GetMapping("/search")
    public Collection<ItemDto> getItemBySearch(@RequestParam String text) {
        log.info("==>Получение Item по поиску со словом : {}", text);
        Collection<ItemDto> itemsBySearch = service.findBySearch(text);
        return itemsBySearch;
    }

    @PostMapping
    public ItemDto create(@RequestHeader(userParmHeader) Long userId,
                          @RequestBody ItemDto itemDto) {
        log.info("==>Создание Item: {} с владельцем {}", itemDto, userId);
        ItemDto newItemDto = service.create(itemDto, userId);
        return newItemDto;
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@RequestHeader(userParmHeader) Long userId,
                          @PathVariable Long itemId,
                          @RequestBody ItemDto itemDto) {
        log.info("==>Обновление Item: {} владельца {}", itemDto, userId);
        itemDto.setId(itemId);
        ItemDto updItemDto = service.update(itemDto, userId);
        return updItemDto;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.info("==>Удаление Item по: {}", id);
        service.delete(id);
    }

    @PostMapping("/{itemId}/comment")
    public CommentDto createComment(@RequestHeader(userParmHeader) Long userId,
                                    @PathVariable Long itemId,
                                    @RequestBody CommentInfoDto commentInfoDto) {
        log.info("==>Создание коментария к Item по: {}", itemId);
        CommentDto commentDto = service.createComment(itemId, userId, commentInfoDto);
        return commentDto;
    }

}