package ua.chernonog.onlinebookstore.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
