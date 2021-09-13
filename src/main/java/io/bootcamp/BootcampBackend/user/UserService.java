package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.exception.EmailExistsException;
import io.bootcamp.BootcampBackend.exception.IncorrectCredentialException;
import io.bootcamp.BootcampBackend.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService( UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addNewUser(User user) throws EmailExistsException {
        List<User> users = selectAllUser();

        if (users.contains(user)) {
            throw new EmailExistsException(
                    "There is an account with that email address:" + user.getEmail());
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        int result = userDAO.insertUser(user);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
    }

    public void removeExistingUser(int id) {

        if (!doesUserWithIDExist(id)) {
            throw new NotFoundException(id + " does not exist");
        }

        int result = userDAO.deleteUser(id);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
    }

    public List<User> selectAllUser(){
        return userDAO.selectAllUser();
    }

    public User selectUserById(int id){
        return userDAO.selectUserById(id)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    public User selectUserByEmail(String email){
        return userDAO.selectUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("user not found"));
    }

    public void updateUser(User user){
        if (doesUserWithIDExist(user.getId())){
            user.setPassword(passwordEncoder().encode(user.getPassword()));
            userDAO.updateUser(user);
        } else{
            throw new NotFoundException(user.getEmail() + " not found");
        }
    }

    public void loginUser(User user) throws IncorrectCredentialException {
        User userInDB = selectUserByEmail(user.getEmail());
        if (userPasswordCheck(user.getPassword(), userInDB )){
            userDAO.loginUser(userInDB.getId());
        } else{
            throw new IncorrectCredentialException("Wrong login information, please try again");
        }
    }

    public void logoutUser(int id){

        if (!doesUserWithIDExist(id)) {
            throw new NotFoundException(id + " does not exist");
        }

        int result = userDAO.logoutUser(id);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }

    }

    public List<User> selectAllOnlineUser(){
        return userDAO.selectAllOnlineUser();
    }



    private boolean doesUserWithIDExist(int id){
        return selectAllUser().stream().anyMatch(p -> p.getId() == id);
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
