package by.ovsyanka.mylist.Rest;

import by.ovsyanka.mylist.Dto.TaskDto;
import by.ovsyanka.mylist.Entity.Task;
import by.ovsyanka.mylist.Logging.Loggable;
import by.ovsyanka.mylist.Service.TaskService;
import by.ovsyanka.mylist.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/tasks/")
@AllArgsConstructor
public class TaskRestController {

    private final TaskService taskService;
    private final UserService userService;

    @Operation(summary = "Get all tasks", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return tasks",
                    content = {@Content(mediaType = "application/json")})
    })
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

    @Operation(summary = "Search tasks by name", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return tasks",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @GetMapping(value = "search/{name}")
    public ResponseEntity<Page<TaskDto>> getTasksByName(
            @PathVariable("name") String name, Principal principal, Pageable pageable) {
        List<Task> tasks = taskService.findAllByUserIdAndName(userService.findByName(principal.getName()).getId(), name);
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

    @Operation(summary = "Add task", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Add task",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @PostMapping(value = "newTask")
    public ResponseEntity<TaskDto> addTask(@Valid @RequestBody TaskDto taskDto, Principal principal) throws Exception {
        taskDto.setUserId(userService.findByName(principal.getName()).getId());
        taskService.addTask(taskDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Update task", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update task",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @PutMapping(value = "{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable("id") Long id, @Valid @RequestBody TaskDto taskDto, Principal principal) {
        taskDto.setUserId(userService.findByName(principal.getName()).getId());
        taskService.updateTask(id, taskDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Delete task", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delete task",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @DeleteMapping(value = "{id}")
    public ResponseEntity<TaskDto> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTaskById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get all task by id", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Return task",
                    content = {@Content(mediaType = "application/json")})
    })
    @Loggable
    @GetMapping(value = "{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(TaskDto.fromTask(taskService.findById(id)), HttpStatus.OK);
    }
}
