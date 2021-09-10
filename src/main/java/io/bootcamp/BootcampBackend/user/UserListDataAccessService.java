package io.bootcamp.BootcampBackend.user;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class UserListDataAccessService implements UserDAO{
    @Override
    public Optional<User> selectUserById(int id) {
        return Optional.of(new User(1, "Silvia", "", ""));
    }

    @Override
    public List<User> selectAllUser() {
        return null;
    }

    @Override
    public int deleteUser(int id) {
        return 0;
    }

    @Override
    public int insertUser(User user) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }
}
