package ru.queue.service;

import ru.queue.domain.Queue;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueService {

    Queue findById(Long id);

    Queue createQueue(String name);

    Queue findByName(String name);

    Queue save(Queue queue);

    List<Queue> findByCreatedAfter(LocalDateTime date);

    List<Queue> findByNameContaining(String nameContaining);
}
