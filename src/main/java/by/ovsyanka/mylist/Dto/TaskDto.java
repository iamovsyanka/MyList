package by.ovsyanka.mylist.Dto;

import by.ovsyanka.mylist.Entity.Task;
import lombok.Data;

import javax.validation.constraints.Null;
import java.util.Date;

@Data
public class TaskDto {
    @Null
    private Long id;

    private String name;
    private String description;
    private Date dateOfCreation;
    private Date dateOfDeadline;

    public Task toTask(){
        Task task = new Task();
        task.setId(id);
        task.setName(name);
        task.setDescription(description);
        task.setDateOfCreation(dateOfCreation);
        task.setDateOfDeadline(dateOfDeadline);

        return task;
    }

    public static TaskDto fromTask(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setDateOfCreation(task.getDateOfCreation());
        taskDto.setDateOfDeadline(task.getDateOfDeadline());

        return taskDto;
    }
}
