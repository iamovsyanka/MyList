package by.ovsyanka.teamwork.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "COMMENTS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"user", "task"})
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComment", nullable = false)
    private Long id;
    @Column(name = "date", nullable = false)
    private Date date;
    @Column(name = "text", nullable = false, length = 150)
    private String text;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    @JsonManagedReference
    private User user;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idTask")
    @JsonManagedReference
    private Task task;
}
