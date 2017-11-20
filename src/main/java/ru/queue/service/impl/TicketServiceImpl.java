package ru.queue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.queue.domain.Queue;
import ru.queue.domain.Ticket;
import ru.queue.domain.User;
import ru.queue.repository.QueueRepository;
import ru.queue.repository.TicketRepository;
import ru.queue.service.TicketService;
import ru.queue.service.UserService;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private QueueRepository queueRepository;

    @Override
    public Ticket getTicket(Queue queue, User user) {
        Queue localQueue = queueRepository.findById(queue.getId());
        Ticket localTicket;
        if(localQueue != null){
            List<Ticket> userTickets = ticketRepository.findByUser(user);
            for(Ticket ticket:userTickets){
                if(ticket.getQueue().getId().equals(queue.getId())){
                    LOG.info("Already have ticket for this queue");
                    localTicket = ticket;
                    return localTicket;
                }
            }
            localTicket = new Ticket();
            localTicket.setQueue(localQueue);
            localTicket.setUser(user);
            localTicket = ticketRepository.save(localTicket);
        } else {
            LOG.info("Queue does not exist");
            return null;
        }
        return localTicket;
    }

    @Override
    public Ticket removeTicket(Queue queue, Ticket ticket, User user) {
        return null;
    }

    @Override
    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }
}
