package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Entity.Task;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Service.TaskService;
import by.ovsyanka.mylist.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping(value = "/api/tasks/")
public class TaskRestController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskRestController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Loggable
    @GetMapping(value = "list")
    public ResponseEntity<Page<TaskDto>> getTasks(
            Principal principal, Pageable pageable) {
        List<Task> tasks = taskService.findAllByUserId(userService.findByName(principal.getName()).getId());
        List<TaskDto> taskDtos = new ArrayList<>();

        for (Task task : tasks) {
            taskDtos.add(TaskDto.fromTask(task));
        }

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), taskDtos.size());
        Page<TaskDto> result
                = new PageImpl<>(taskDtos.subList(start, end), pageable, taskDtos.size());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Loggable
    @PostMapping(value = "newTask")
    public ResponseEntity<TaskDto> addTask(@Valid @RequestBody TaskDto taskDto, Principal principal) throws Exception {
        taskDto.setUserId(userService.findByName(principal.getName()).getId());
        taskService.addTask(taskDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @PutMapping(value = "{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskDto taskDto, Principal principal) {
        taskDto.setUserId(userService.findByName(principal.getName()).getId());
        taskService.updateTask(id, taskDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @DeleteMapping(value = "{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Loggable
    @GetMapping(value = "{id}")
    public ResponseEntity<TaskDto> getTask(@PathVariable("id") Long id) {
        taskService.findById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
