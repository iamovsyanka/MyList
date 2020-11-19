package by.ovsyanka.mylist.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "taskId", nullable = false)
    private Long taskId;

    @Column(name = "taskName", nullable = false)
    private String taskName;

    @Column(name = "taskDescription")
    private String taskDescription;

    @Column(name = "dateOfCreation")
    private String dateOfCreation;

    @Column(name = "dateOfDeadline")
    private String dateOfDeadline;
}
