package ru.queue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.queue.domain.Queue;
import ru.queue.repository.QueueRepository;
import ru.queue.service.QueueService;
import ru.queue.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class QueueServiceImpl implements QueueService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public Queue findById(Long id) {
        return queueRepository.findById(id);
    }

    @Override
    public Queue createQueue(String name) {
        Queue localQueue = queueRepository.findByName(name);

        if(localQueue != null){
            LOG.info("Queue already exists. Nothing will be done",localQueue.getName());
        } else {
            localQueue = new Queue();
            localQueue.setName(name);
            localQueue = queueRepository.save(localQueue);
        }
        return localQueue;
    }

    @Override
    public Queue findByName(String name) {
        return queueRepository.findByName(name);
    }

    @Override
    public Queue save(Queue queue) {
        return queueRepository.save(queue);
    }

    @Override
    public List<Queue> findByCreatedAfter(LocalDateTime date) {
        return queueRepository.findByCreatedAfter(date);
    }

    @Override
    public List<Queue> findByNameContaining(String nameContaining) {
        return queueRepository.findByNameContaining(nameContaining);
    }

    @Override
    public List<Queue> findLastTen() {
        Long lastTenId = queueRepository.findTopByOrderByIdDesc().getId() - 10;
        if (lastTenId < 1) {
            lastTenId = (long) 1;
        }
        return queueRepository.findByIdGreaterThan(lastTenId);
    }
}
