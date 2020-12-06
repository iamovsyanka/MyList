package by.ovsyanka.mylist.Service;

import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TaskService {
    Task findById(Long id);
    Task findByName(String name);
    Page<Task> findAllByUserId(Long id, Pageable pageable);
    void deleteTaskById(Long id);
    void addTask(TaskDto task) throws Exception;
    void updateTask(Long id, TaskDto task);
}
