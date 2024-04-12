package ua.chernonog.onlinebookstore.repository.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.Role;
import ua.chernonog.onlinebookstore.entity.RoleName;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
