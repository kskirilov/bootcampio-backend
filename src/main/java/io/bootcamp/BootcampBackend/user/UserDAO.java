package io.bootcamp.BootcampBackend.user;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> selectUserById(int id);
    Optional<User> selectUserByEmail(String email);
    List<User> selectAllUser();
    int deleteUser(int id);
    int insertUser (User user);
    int updateUser(User user);
    int loginUser(int id);
    int logoutUser(int id);
    List<User> selectAllOnlineUser();
}
