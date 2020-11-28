package by.ovsyanka.mylist.Dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Null;
import java.util.Date;

@Getter
@Setter
public class TaskDto {
    @Null
    private Long taskId;

    private String name;
    private String description;
    private Date dateOfCreation;
    private Date dateOfDeadline;
}
