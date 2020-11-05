package by.ovsyanka.teamwork.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "PROJECT")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"tasks","devGroup"})
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProject", nullable = false)
    private Long id;
    @Column(name = "nameProject", nullable = false)
    private String name;
    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Task> tasks;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idDevGroup")
    @JsonManagedReference
    private DevGroup devGroup;
}
