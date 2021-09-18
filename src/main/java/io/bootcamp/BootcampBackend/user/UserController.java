package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import io.bootcamp.BootcampBackend.exception.IncorrectCredentialException;
import io.bootcamp.BootcampBackend.util.Response;
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
    public Response removeUser(@PathVariable int id){
        return userService.removeExistingUser(id);
    }

    @PutMapping
    public Response updateUser(@RequestBody User user){
        return userService.updateUser(user);
    }


    @PostMapping("/login")
    public Response loginUser(@RequestBody User user) throws IncorrectCredentialException {
        return userService.loginUser(user);
    }

    @DeleteMapping("/login")
    public Response logoutUser(@RequestParam int userID){

        return userService.logoutUser(userID);
    }

    @GetMapping("/login")
    public List<User> selectAllOnlineUser(){
        return userService.selectAllOnlineUser();
    }

}
