package by.ovsyanka.teamwork.repositories;

import by.ovsyanka.teamwork.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository  extends JpaRepository<Task, Long> {
    Optional<Task> findTaskByName(String name);
}
