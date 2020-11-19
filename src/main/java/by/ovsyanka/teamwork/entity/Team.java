package by.ovsyanka.teamwork.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "teams")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teamId", nullable = false)
    private Long id;

    @Column(name = "teamName", nullable = false, length = 100)
    private String name;

    @Column(name = "teamDescription")
    private String description;


}