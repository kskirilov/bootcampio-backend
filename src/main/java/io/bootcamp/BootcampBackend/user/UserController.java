package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import io.bootcamp.BootcampBackend.exception.IncorrectCredentialException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/users")
public class UserController {
    private  UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> selectAllUser(@RequestParam(defaultValue = "id") String sort){
        return userService.selectAllUsers(sort);
    }

    @GetMapping("{id}")
    public UserDTO selectUserById(@PathVariable("id") int id){
        return userService.selectUserById(id);
    }


    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) throws AlreadyExistsException {
        userService.addNewUser(userDTO);
    }

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable int id){
        userService.removeExistingUser(id);
    }

    @PutMapping
    public void updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
    }


    @PostMapping("/login")
    public void loginUser(@RequestBody UserDTO userDTO) throws IncorrectCredentialException {
        userService.loginUser(userDTO);
    }

    @DeleteMapping("/login")
    public void logoutUser(@RequestBody UserDTO userDTO){
        userService.logoutUser(userDTO);
    }

    @GetMapping("/login")
    public List<UserDTO> selectAllOnlineUser(){
        return userService.selectAllOnlineUsers();
    }

}
