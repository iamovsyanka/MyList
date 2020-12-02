package by.ovsyanka.mylist.Repository;

import by.ovsyanka.mylist.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByUserId(Long id);
    Task findByName(String name);
    void deleteTaskById(Long id);
    Optional<Task> findById(Long id);
}
