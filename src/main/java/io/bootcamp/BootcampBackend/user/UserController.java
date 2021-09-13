package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.exception.EmailExistsException;
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
    public List<User> selectAllUser(){
        return userService.selectAllUser();
    }

    @GetMapping("{id}")
    public User selectUserById(@PathVariable("id") int id){
        return userService.selectUserById(id);
    }


    @PostMapping
    public void addUser(@RequestBody User user) throws EmailExistsException {
        userService.addNewUser(user);
    }

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable("id") int id){
        userService.removeExistingUser(id);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }


    @PostMapping("/login")
    public void loginUser(@RequestBody User user) throws IncorrectCredentialException {
        userService.loginUser(user);
    }

    @DeleteMapping("/login/{id}")
    public void logoutUser(@PathVariable("id") int id){
        userService.logoutUser(id);
    }

    @GetMapping("/login")
    public List<User> selectAllOnlineUser(){
        return userService.selectAllOnlineUser();
    }

}
