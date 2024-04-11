package ua.chernonog.onlinebookstore.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.chernonog.onlinebookstore.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
