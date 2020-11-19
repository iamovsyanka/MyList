package by.ovsyanka.teamwork.repositories;

import by.ovsyanka.teamwork.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository  extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findRoleByName(String name);
}
