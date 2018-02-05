package ru.queue.domain.ajax;

import ru.queue.domain.Ticket;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Date;

public class AjaxTicket {

    private long id;
    private int place;
    private String name;
    private Date created;

    public AjaxTicket(Ticket ticket) {
        id = ticket.getQueue().getId();
        place = ticket.getRelativePosition();
        name = ticket.getQueue().getName();
        Instant instant = ticket.getIssued().toInstant(ZoneOffset.ofHours(+3));
        created = Date.from(instant);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
