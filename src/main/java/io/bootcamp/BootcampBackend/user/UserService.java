package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import io.bootcamp.BootcampBackend.exception.IncorrectCredentialException;
import io.bootcamp.BootcampBackend.exception.NotFoundException;
import io.bootcamp.BootcampBackend.util.Response;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService( UserDAO userDAO) {
         this.userDAO = userDAO;
    }

    public Response addNewUser(User user) throws AlreadyExistsException {

        List<User> users = selectAllUser("id");

        if (users.contains(user)) {
            throw new AlreadyExistsException(
                    "There is an account with that email address:" + user.getEmail());
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        int result = userDAO.insertUser(user);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
        return new Response(user.getId(), "New user added to database");
    }

    public Response removeExistingUser(int id) {

        if (!doesUserWithIDExist(id)) {
            throw new NotFoundException(id + " does not exist");
        }

        int result = userDAO.deleteUser(id);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }

        return new Response(id, "User " + id + " removed from database successfully");
    }

    public List<User> selectAllUser(String input){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<User> users = userDAO.selectAllUserSortBy(input);

        users.stream().forEach(person ->
                person.setCreatedAt(LocalDateTime.parse(person.getCreatedAt().format(formatter), formatter)));

        users.stream().forEach(person ->
                person.setUpdatedAt(LocalDateTime.parse(person.getUpdatedAt().format(formatter), formatter)));

        return users;
    }

    public User selectUserById(int id){
        return userDAO.selectUserById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    public User selectUserByEmail(String email){
        return userDAO.selectUserByEmail(email)
                .orElseThrow(() -> new NotFoundException(email + " not found"));
    }

    public Response updateUser(User user){
        if (user == null || user.getId() == 0) {
            throw new NotFoundException("Missing Data Exception");}

        if (doesUserWithIDExist(user.getId())){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userDAO.updateUser(user);
            return new Response(user.getId(), "User " + user.getId() + " updated successfully");
        } else{
            throw new NotFoundException(user.getEmail() + " not found");
        }
    }

    public Response loginUser(User user) throws IncorrectCredentialException {

        if(selectAllOnlineUser().stream().anyMatch(person -> person.getEmail().equals(user.getEmail()))){
            throw new AlreadyExistsException(user.getEmail() + " is already logged in!");
        }

        User userInDB = selectUserByEmail(user.getEmail());
        if (userPasswordCheck(user.getPassword(), userInDB )){
            userDAO.loginUser(userInDB.getId());
            return new Response(user.getId(), "User " + user.getId() + " logged in successfully");
        } else{
            throw new IncorrectCredentialException("Wrong login information, please try again");
        }

    }

    public Response logoutUser(int id){

        if (!doesUserWithIDExist(id)) {
            throw new NotFoundException(id + " does not exist");
        }

        int result = userDAO.logoutUser(id);

        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
        return new Response(id, "User " + id + " logged out successfully");
    }

    public List<User> selectAllOnlineUser(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<User> users = userDAO.selectAllOnlineUser();
        users.stream().forEach(person ->
                person.setLastSeen(LocalDateTime.parse(person.getLastSeen().format(formatter), formatter)));

        return users;
    }


    private boolean doesUserWithIDExist(int id){
        return selectAllUser("id").stream().anyMatch(p -> p.getId() == id);
    }



    public PasswordEncoder passwordEncoder() {
        int strength = 10; // work factor of bcrypt
        return new BCryptPasswordEncoder(strength, new SecureRandom());
    }

    public boolean userPasswordCheck(String password, User user) {
        String encodedPassword = user.getPassword();
        return passwordEncoder().matches(password, encodedPassword);
    }


}
