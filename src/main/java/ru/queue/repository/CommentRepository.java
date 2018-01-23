package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.Comment;
import ru.queue.domain.Queue;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    Comment findById(Long id);

    List<Comment> findByQueue(Queue queue);
}
