package by.ovsyanka.teamwork.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "DEVGROUPS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"team","tasks","users"})
@AllArgsConstructor
@NoArgsConstructor
public class DevGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDevGroup", nullable = false)
    private Long id;
    @Column(name = "nameDevGroup", nullable = false, length = 150)
    private String name;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idTeam")
    @JsonManagedReference
    private Team team;
    @OneToMany(mappedBy = "devGroup", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<Task> tasks;
    @OneToMany(mappedBy = "devGroup", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<User> users;

}
