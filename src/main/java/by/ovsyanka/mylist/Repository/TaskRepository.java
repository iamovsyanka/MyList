package by.ovsyanka.mylist.Repository;

import by.ovsyanka.mylist.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
    Page<Task> findAllByUserId(Long id, Pageable pageable);
    Task findByName(String name);
    void deleteTaskById(Long id);
    Optional<Task> findById(Long id);
}
