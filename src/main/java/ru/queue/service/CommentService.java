package ru.queue.service;

import ru.queue.domain.Comment;
import ru.queue.domain.Queue;

import java.util.List;

public interface CommentService {

    Comment findById(Long id);

    List<Comment> findByQueue(Queue queue);

    Comment save(Comment comment);

    void delete(Comment comment);
}
