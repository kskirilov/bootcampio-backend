package io.bootcamp.BootcampBackend.user;

import io.bootcamp.BootcampBackend.exception.AlreadyExistsException;
import io.bootcamp.BootcampBackend.exception.IncorrectCredentialException;
import io.bootcamp.BootcampBackend.exception.NotFoundException;
import io.bootcamp.BootcampBackend.wishlist.WishlistRepository;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserManagement{
    private final UserRepository userRepository;
    private final WishlistRepository wishlistRepository;

    public UserService(UserRepository userRepository, WishlistRepository wishlistRepository) {
         this.userRepository = userRepository;
         this.wishlistRepository = wishlistRepository;
    }

    @Transactional
    @Override
    public void addNewUser(UserDTO userDTO) throws AlreadyExistsException {
        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()){
            throw new AlreadyExistsException("User already exists!");
        }

        User user = new User();
        mapDtoToEntityPOST(userDTO, user);
        User result = userRepository.save(user);
        if (!user.equals(result)) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
    }

    @Transactional
    @Override
    public void removeExistingUser(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("User does not exist");
        }

        //Cannot delete if user has courses on wishlist

        int result = userRepository.deleteUserById(id);
        if (result != 1) {
            throw new IllegalStateException("oops something went wrong with the database");
        }
    }

    @Override
    public List<UserDTO> selectAllUsers(String input){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<User> usersInRepo = userRepository.findAll(Sort.by(input));

        List<UserDTO> users = new ArrayList<>();

        usersInRepo.stream().forEach(
                user -> {
                    UserDTO userDTO = mapEntityToDTO(user);
                    users.add(userDTO);
                }
        );

        users.stream().forEach(person ->
                person.setCreatedAt(LocalDateTime.parse(person.getCreatedAt().format(formatter), formatter)));

        users.stream().forEach(person ->{
            if(person.getUpdatedAt()!=null){
                person.setUpdatedAt(LocalDateTime.parse(person.getUpdatedAt().format(formatter), formatter));
            }});

        return users;
    }

    @Override
    public UserDTO selectUserById(int id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NotFoundException("User not found"));

        return mapEntityToDTO(user);
    }

    @Override
    public UserDTO selectUserByEmail(String email){
        User user = userRepository.findUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User not found"));

        return mapEntityToDTO(user);
    }

    @Transactional
    @Override
    public void updateUser(UserDTO userDTO){
        if (userDTO == null || userDTO.getId() == 0) {
            throw new NotFoundException("Missing Data Exception");}

        if (!userRepository.findById(userDTO.getId()).isEmpty()){
            User user = userRepository.getById(userDTO.getId());
            mapDtoToEntityPUT(userDTO, user);
            userRepository.save(user);
        } else{
            throw new NotFoundException("User not found");
        }
    }

    @Override
    public List<UserDTO> selectAllOnlineUsers(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        List<User> usersInRepo = userRepository.findAll().stream().filter(p->p.getLastSeen()!=null).toList();

        List<UserDTO> users = new ArrayList<>();
        usersInRepo.stream().forEach(
                p -> {
                    users.add(mapEntityToDTO(p));
                }
        );
        users.stream().forEach(person ->
                person.setLastSeen(LocalDateTime.parse(person.getLastSeen().format(formatter), formatter)));



        return users;
    }

    @Transactional
    @Override
    public void loginUser(UserDTO userDTO) throws IncorrectCredentialException {

        if(selectAllOnlineUsers().stream().anyMatch(person -> person.getEmail().equals(userDTO.getEmail()))){
            throw new AlreadyExistsException("User is already logged in!");
        }

        User userInDB = selectUserByEmailInDb(userDTO.getEmail());
        if (userPasswordCheck(userDTO.getPassword(), userInDB )){
            userInDB.setLastSeen(LocalDateTime.now());
            userRepository.save(userInDB);
        } else{
            throw new IncorrectCredentialException("Wrong login information, please try again");
        }

    }

    @Transactional
    @Override
    public void logoutUser(UserDTO userDTO){

        if(!selectAllOnlineUsers().stream().anyMatch(person -> person.getEmail().equals(userDTO.getEmail()))){
            throw new NotFoundException("User was not logged in");
        }

        User user = selectUserByEmailInDb(userDTO.getEmail());
        user.setLastSeen(null);
        userRepository.save(user);

    }

    private User selectUserByEmailInDb(String email){
        return userRepository.findUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User not found"));
    }

    public PasswordEncoder passwordEncoder() {
        int strength = 10; // work factor of bcrypt
        return new BCryptPasswordEncoder(strength, new SecureRandom());
    }

    public boolean userPasswordCheck(String password, User user) {
        String encodedPassword = user.getPassword();
        return passwordEncoder().matches(password, encodedPassword);
    }

    private void mapDtoToEntityPUT(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder().encode(userDTO.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());
    }

    private void mapDtoToEntityPOST(UserDTO userDTO, User user) {
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder().encode(userDTO.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
    }

    private UserDTO mapEntityToDTO(User user) {
        UserDTO responseDTO = new UserDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPassword(user.getPassword());
        responseDTO.setCreatedAt(user.getCreatedAt());
        responseDTO.setUpdatedAt(user.getUpdatedAt());
        responseDTO.setLastSeen(user.getLastSeen());
        return responseDTO;
    }


}
