package ru.queue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.queue.domain.Queue;
import ru.queue.repository.QueueRepository;
import ru.queue.service.QueueService;
import ru.queue.service.UserService;
import ru.queue.utility.DateUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

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
    public Set<Queue> findByCreatedAfter(LocalDateTime date) {
        return queueRepository.findByCreatedAfter(date);
    }
}
