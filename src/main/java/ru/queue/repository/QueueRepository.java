package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.Queue;
import java.time.LocalDateTime;
import java.util.Set;

public interface QueueRepository extends CrudRepository<Queue, Long> {

    Queue findById(Long id);

    Queue findByName(String name);

    Set<Queue> findByCreatedAfter(LocalDateTime date);
}
