package user.repository;

import user.model.User;

import java.util.Collection;
import java.util.Optional;

public interface UserRepository {
    User create(User user);

    User update(long id,User updUser);

    void delete(long id);

    Optional<User> findById(long id);

    Collection<User> getListUserSameEmail(String email,long id);

    Optional<User> getUserByEmail(String email);

}