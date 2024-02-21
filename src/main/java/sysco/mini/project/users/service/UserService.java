package sysco.mini.project.users.service;
import sysco.mini.project.users.dto.AddUserRequest;
import sysco.mini.project.users.model.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User saveUser(AddUserRequest addUserRequest);
    User findUserById(Long id);
    User updateUser(Long id, String name, String email);
    void deleteUser(Long id);
}
