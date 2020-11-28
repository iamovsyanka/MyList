package by.ovsyanka.mylist.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "task_name", nullable = false)
    private String name;

    @Column(name = "task_description")
    private String description;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    @Column(name = "date_of_deadline")
    private Date dateOfDeadline;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
}
