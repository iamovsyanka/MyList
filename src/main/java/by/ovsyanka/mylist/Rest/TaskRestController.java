package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Entity.Task;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Service.TaskService;
import by.ovsyanka.mylist.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<List<TaskDto>> getTasks(Principal principal) {
        List<Task> tasks = taskService.findAllByUserId(userService.findByName(principal.getName()).getId());
        List<TaskDto> result = new ArrayList<>();

        for (Task task : tasks) {
            result.add(TaskDto.fromTask(task));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
