package by.ovsyanka.mylist.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tasks")
public class Task extends BaseEntity {
    @Column(name = "task_name", nullable = false)
    private String name;

    @Column(name = "task_description")
    private String description;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "date_of_deadline")
    private Date dateOfDeadline;

    @ManyToOne(fetch=FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
