package io.bootcamp.BootcampBackend.user;

import java.util.List;
import java.util.Optional;

public class UserDataAccessService implements UserDAO {
    @Override
    public Optional<User> selectUserById(int id) {
        return Optional.empty();
    }

    @Override
    public List<User> selectAllUser() {
        return null;
    }

    @Override
    public int deletePerson(int id) {
        return 0;
    }
}
