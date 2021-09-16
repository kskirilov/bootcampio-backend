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
    public List<User> selectAllUser(@RequestParam(defaultValue = "id") String sort){
        return userService.selectAllUser(sort);
    }

    @GetMapping("{id}")
    public User selectUserById(@PathVariable("id") int id){
        return userService.selectUserById(id);
    }


    @PostMapping
    public void addUser(@RequestBody User user) throws AlreadyExistsException {
        userService.addNewUser(user);
    }

    @DeleteMapping("{id}")
    public void removeUser(@PathVariable int id){
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

    @DeleteMapping("/login")
    public void logoutUser(@RequestParam int userID){
        userService.logoutUser(userID);
    }

    @GetMapping("/login")
    public List<User> selectAllOnlineUser(){
        return userService.selectAllOnlineUser();
    }

}
