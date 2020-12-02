package by.ovsyanka.mylist.Service;

import by.ovsyanka.mylist.Entity.Task;

import java.util.List;

public interface TaskService {
    Task findByName(String name);
    List<Task> findAllByUserId(Long id);
    void deleteTaskById(Long id);
}
