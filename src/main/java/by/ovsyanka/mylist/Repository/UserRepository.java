package by.ovsyanka.mylist.Repository;

import by.ovsyanka.mylist.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    Optional<User> findById(Long Id);
    List<User> findAll();
}
