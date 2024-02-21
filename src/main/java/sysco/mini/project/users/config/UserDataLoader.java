package sysco.mini.project.users.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import sysco.mini.project.users.dao.UserDAO;
import sysco.mini.project.users.enums.UserRole;
import sysco.mini.project.users.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataLoader {

    @Bean
    CommandLineRunner commandLineRunner(UserDAO userDAO) {
        return args -> {
            List<User> users = new ArrayList<>();
            users.add(new User(
                    1L,
                    "Sachintha Gunathilaka",
                    "sachintha@gmail.com",
                    UserRole.CUSTOMER
            ));
            users.add(new User(
                    2L,
                    "Kanishka Dilhan",
                    "kanishka@gmail.com",
                    UserRole.CUSTOMER
            ));
            users.add(new User(
                    3L,
                    "Ishara Nawarathna",
                    "ishara@gmail.com",
                    UserRole.SUPPLIER
            ));
            userDAO.saveAll(users);
        };

    }
}