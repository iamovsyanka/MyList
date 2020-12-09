package by.ovsyanka.mylist.Service;

import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Entity.Task;

import java.util.List;

public interface TaskService {
    Task findById(Long id);
    Task findByName(String name);
    List<Task> findAllByUserId(Long id);
    void deleteTaskById(Long id);
    void addTask(TaskDto task) throws Exception;
    void updateTask(Long id, TaskDto task);
}
