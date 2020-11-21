package by.ovsyanka.mylist.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "commentId", nullable = false)
    private Long commentId;

    @Column(name = "commentContent", nullable = false)
    private String commentContent;

    @Column(name = "dateOfCreation", nullable = false)
    private Date dateOfCreation;



}
