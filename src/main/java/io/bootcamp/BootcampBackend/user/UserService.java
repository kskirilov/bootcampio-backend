package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.execption.NotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService( UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addNewUser(User user){
        List<User> users = selectAllUser();

        if (users.contains(user)) {
            throw new IllegalStateException(user + " already exist");
        }

        int result = userDAO.insertUser(user);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
    }

    public void removeExistingUser(int id) {

        if (!doesUserWithIDExist(id)) {
            throw new IllegalStateException(id + " does not exist");
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

    public void updateUser(User user){
        if (doesUserWithIDExist(user.getId())){
            userDAO.updateUser(user);
        } else{
            throw new NotFoundException("user not found");
        }
    }

    private boolean doesUserWithIDExist(int id){
        return selectAllUser().stream().anyMatch(p -> p.getId() == id);
    }
}
