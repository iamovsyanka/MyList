package by.ovsyanka.mylist.Entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Table(name = "userroles")
public class UserRole {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "roleId", nullable = false)
    private Long roleId;

    @Column(name = "role", nullable = false)
    private String role;

    @OneToMany(mappedBy = "userRole", fetch = FetchType.EAGER)
    private Collection<User> users;
}
