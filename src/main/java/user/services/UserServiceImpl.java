package user.services;

import exceptions.NotFoundException;
import exceptions.ValidatetionConflict;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import user.dto.UserDto;
import user.mapper.UserMapper;
import user.model.User;
import user.repository.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDto create(UserDto newUserDto) {
        if (userRepository.getUserByEmail(newUserDto.getEmail()).isPresent()) {
            throw new ValidatetionConflict("Пользователь с таким email уже зарегистрирован");
        }
        User user = UserMapper.toUser(newUserDto);
        User createdUser = userRepository.create(user);
        return UserMapper.toUserDto(createdUser);
    }

    @Override
    public UserDto update(long id, UserDto userUpdDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь " + id + " не найден"));

        if (userUpdDto.getEmail() != null && !userUpdDto.getEmail().equals(existingUser.getEmail())) {
            userRepository.getUserByEmail(userUpdDto.getEmail())
                    .ifPresent(user -> {
                        if (user.getId() != id) {
                            throw new ValidatetionConflict("Пользователь с таким email уже зарегистрирован");
                        }
                    });
        }

        User userToUpdate = UserMapper.toUser(userUpdDto);
        User updatedUser = userRepository.update(id, userToUpdate);
        return UserMapper.toUserDto(updatedUser);
    }

    @Override
    public void delete(long id) {
        if (userRepository.findById(id).isEmpty()) { // Use `isEmpty()` for clarity
            throw new NotFoundException("Пользователь " + id + " не найден");
        }
        userRepository.delete(id);
    }

    @Override
    public UserDto findById(long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Пользователь " + id + " не найден"));
        return UserMapper.toUserDto(user);
    }
}