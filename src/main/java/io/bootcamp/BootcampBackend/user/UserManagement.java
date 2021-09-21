package io.bootcamp.BootcampBackend.user;

import java.util.List;

public interface UserManagement {
    List<UserDTO> selectAllUsers(String input);
    UserDTO selectUserById(int id);
    UserDTO selectUserByEmail(String email);
    void addNewUser(UserDTO userDTO);
    void removeExistingUser(int userId);
    void updateUser(UserDTO userDTO);
    List<UserDTO> selectAllOnlineUsers();
    void loginUser(UserDTO userDTO);
    void logoutUser(UserDTO userDTO);


}
