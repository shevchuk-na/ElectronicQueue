package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.Queue;

import java.time.LocalDateTime;
import java.util.List;

public interface QueueRepository extends CrudRepository<Queue, Long> {

    Queue findById(Long id);

    Queue findTopByOrderByIdDesc();

    Queue findByName(String name);

    List<Queue> findByCreatedAfter(LocalDateTime date);

    List<Queue> findByNameContaining(String name);

    List<Queue> findByIdGreaterThan(Long id);
}
