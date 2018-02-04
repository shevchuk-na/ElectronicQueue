package ru.queue.domain.ajax;

import ru.queue.domain.Ticket;

import java.time.LocalDateTime;

public class AjaxTicket {

    private int place;
    private String name;
    private LocalDateTime created;

    public AjaxTicket(Ticket ticket) {
        place = ticket.getRelativePosition();
        name = ticket.getQueue().getName();
        created = ticket.getIssued();
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
