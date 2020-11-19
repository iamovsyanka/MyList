package by.ovsyanka.teamwork.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "tasks")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTask", nullable = false)
    private Long id;

    @Column(name = "nameTask", nullable = false, length = 150)
    private String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser")
    @JsonManagedReference
    private User user;

    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Comment> comments;
}
