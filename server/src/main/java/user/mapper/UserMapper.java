package user.mapper;

import org.mapstruct.Mapper;
import user.dto.UserDto;
import user.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto(user.getId(),
                user.getName(),
                user.getEmail());
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User(userDto.getId(),
                userDto.getName(),
                userDto.getEmail());
        return user;
    }
}