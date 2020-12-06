package by.ovsyanka.mylist.Service.impl;

import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Entity.Task;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Repository.TaskRepository;
import by.ovsyanka.mylist.Repository.UserRepository;
import by.ovsyanka.mylist.Service.TaskService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskServiceImpl(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).get();
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

    @Override
    @Loggable
    @Transactional
    public void deleteTaskById(Long id) {
        taskRepository.deleteTaskById(id);
    }

    @Override
    @Loggable
    public void addTask(TaskDto taskDto) {
        Task task = TaskDto.toTask(taskDto);
        task.setDateOfCreation(new Date());
        task.setUser(userRepository.findById(taskDto.getUserId()).get());

        taskRepository.save(task);
    }

    @Override
    @Loggable
    public void updateTask(Long id, TaskDto task) {
        Task updatedTask = taskRepository.findById(id).get();
        updatedTask.setUser(userRepository.findById(task.getUserId()).get());

        updatedTask.setName(task.getName());
        updatedTask.setDescription(task.getDescription());
        updatedTask.setDateOfDeadline(task.getDateOfDeadline());

        taskRepository.save(updatedTask);
    }
}
