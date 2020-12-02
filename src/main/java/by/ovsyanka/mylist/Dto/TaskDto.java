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

    public static Task toTask(TaskDto taskDto){
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setDateOfCreation(taskDto.dateOfCreation);
        task.setDateOfDeadline(taskDto.dateOfDeadline);

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
