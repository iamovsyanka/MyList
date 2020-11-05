package by.ovsyanka.teamwork.repositories;

import by.ovsyanka.teamwork.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByName(String name);
}
