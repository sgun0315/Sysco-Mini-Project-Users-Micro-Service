package sysco.mini.project.users.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sysco.mini.project.users.dto.AddUserRequest;
import sysco.mini.project.users.dto.UpdateUserRequest;
import sysco.mini.project.users.model.User;
import sysco.mini.project.users.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findUsers() {
        List<User> users = this.userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> saveProduct(@Valid @RequestBody AddUserRequest addUserRequest) {
        User savedUser = userService.saveUser(addUserRequest);

        logger.info("User saved with ID: {}", savedUser.getUserId());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable Long userId) {
        User user = userService.findUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<User> updateUserById(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        User savedUser = this.userService.updateUser(userId, updateUserRequest.getName(), updateUserRequest.getEmail());

        logger.info("User updated with ID: {}", savedUser.getUserId());
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
        this.userService.deleteUser(userId);

        logger.info("User deleted with ID: {}", userId);
        return ResponseEntity.ok().body("User deleted successfully");
    }
}
