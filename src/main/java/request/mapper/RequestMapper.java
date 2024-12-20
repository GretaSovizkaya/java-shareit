package request.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import request.dto.RequestDto;
import request.model.ItemRequest;
/*@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestMapper {
    public static RequestDto toItemRequestDto(ItemRequest itemRequest) {
        return new RequestDto(itemRequest.getDescription(),
                itemRequest.getRequestor(),
                itemRequest.getCreated());
    }
}