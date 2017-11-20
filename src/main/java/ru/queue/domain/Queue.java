package ru.queue.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User queueAdmin;
    @OneToMany(mappedBy = "queue", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Ticket> ticketList;
    private int timeout;
    private int activeTickets;
    private int ticketsTotal;
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getQueueAdmin() {
        return queueAdmin;
    }

    public void setQueueAdmin(User queueAdmin) {
        this.queueAdmin = queueAdmin;
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getActiveTickets() {
        return activeTickets;
    }

    public void setActiveTickets(int activeTickets) {
        this.activeTickets = activeTickets;
    }

    public int getTicketsTotal() {
        return ticketsTotal;
    }

    public void setTicketsTotal(int ticketsTotal) {
        this.ticketsTotal = ticketsTotal;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
