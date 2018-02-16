package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.Queue;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueRepository extends CrudRepository<Queue, Long> {

    Queue findById(Long id);

    List<Queue> findTop10ByOrderByIdDesc();

    Queue findByName(String name);

    List<Queue> findByCreatedAfter(LocalDateTime date);

    List<Queue> findByNameContaining(String name);
}
