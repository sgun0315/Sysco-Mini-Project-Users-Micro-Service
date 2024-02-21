package sysco.mini.project.users.service.impl;
import org.springframework.stereotype.Service;
import sysco.mini.project.users.dao.UserDAO;
import sysco.mini.project.users.dto.AddUserRequest;
import sysco.mini.project.users.exceptions.UserAlreadyExistsException;
import sysco.mini.project.users.exceptions.UserNotFoundException;
import sysco.mini.project.users.model.User;
import sysco.mini.project.users.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User saveUser(AddUserRequest addUserRequest) {
        Optional<User> existingUser = userDAO.findByEmail(addUserRequest.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User with email " + addUserRequest.getEmail() + " already exists");
        }
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setEmail(addUserRequest.getEmail());
        user.setRole(addUserRequest.getRole());
        return userDAO.save(user);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userOptional = this.userDAO.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public User updateUser(Long id, String name, String email) {
        Optional<User> userOptional = this.userDAO.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setName(name);
            user.setEmail(email);
            return userDAO.save(user);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOptional = this.userDAO.findById(id);
        if (userOptional.isPresent()) {
            this.userDAO.deleteById(id);
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }

    }
}
