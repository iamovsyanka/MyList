package by.ovsyanka.mylist.Service.impl;

import by.ovsyanka.mylist.Entity.Task;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Repository.TaskRepository;
import by.ovsyanka.mylist.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    @Loggable
    public Task findByName(String name) {
        return taskRepository.findByName(name);
    }

    @Override
    @Loggable
    public List<Task> findAllByUserId(Long id) {
        return taskRepository.findAllByUserId(id);
    }
}
