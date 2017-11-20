package ru.queue.service;

import ru.queue.domain.Queue;

import java.time.LocalDateTime;
import java.util.Set;

public interface QueueService {

    Queue findById(Long id);

    Queue createQueue(String name);

    Queue findByName(String name);

    Queue save(Queue queue);

    Set<Queue> findByCreatedAfter(LocalDateTime date);

    Set<Queue> findByNameContaining(String nameContaining);
}
