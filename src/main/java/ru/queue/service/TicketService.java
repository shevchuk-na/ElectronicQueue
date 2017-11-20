package ru.queue.service;

import ru.queue.domain.Queue;
import ru.queue.domain.Ticket;
import ru.queue.domain.User;

public interface TicketService {

    Ticket getTicket(Queue queue, User user);

    Ticket removeTicket(Queue queue, Ticket ticket, User user);

    Ticket save(Ticket ticket);
}
