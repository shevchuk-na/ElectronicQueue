package ru.queue.repository;

import org.springframework.data.repository.CrudRepository;
import ru.queue.domain.Ticket;
import ru.queue.domain.User;

import java.util.List;

public interface TicketRepository extends CrudRepository<Ticket, Long>{

    Ticket findById(Long id);

    List<Ticket> findByUser(User user);
}
