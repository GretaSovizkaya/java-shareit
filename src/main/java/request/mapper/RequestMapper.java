package request.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import request.dto.ItemRequestDto;
import request.model.ItemRequest;
import user.mapper.UserMapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface RequestMapper {
    @Mapping(target = "requestor", source = "requestor.id")
    @Mapping(target = "created", expression = "java(itemRequest.getCreated().toLocalDate())")
    ItemRequestDto toItemRequestDto(ItemRequest itemRequest);

    @Mapping(target = "requestor", ignore = true) // Связь с User устанавливается в сервисе
    @Mapping(target = "id", ignore = true) // ID создается автоматически
    ItemRequest toItemRequest(ItemRequestDto itemRequestDto);
}
