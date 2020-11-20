package by.ovsyanka.mylist.Repository;

import by.ovsyanka.mylist.Entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    UserRole getRoleByRole(String name);
}
