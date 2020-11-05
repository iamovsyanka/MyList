package by.ovsyanka.teamwork.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "TEAMS")
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"project","devGroups"})
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTeam", nullable = false)
    private Long id;
    @Column(name = "nameTeam", nullable = false, length = 150)
    private String name;
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<User> users;
    @OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
    @JsonBackReference
    private Collection<DevGroup>  devGroups;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "idProject")
    @JsonManagedReference
    private Project project;
}