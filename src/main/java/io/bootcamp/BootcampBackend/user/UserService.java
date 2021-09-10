package io.bootcamp.BootcampBackend.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDataAccessService userDataAccessService) {
        this.userDAO = userDataAccessService;
    }

    public void addNewUser(User user){
        // TODO: check if user exists
        int result = userDAO.insertUser(user);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong");
        }
    }
}
