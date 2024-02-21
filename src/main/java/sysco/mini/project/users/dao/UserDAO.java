package sysco.mini.project.users.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sysco.mini.project.users.model.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
