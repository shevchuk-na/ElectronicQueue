package ru.queue.domain.ajax;

import ru.queue.domain.Ticket;

import java.util.ArrayList;
import java.util.List;

public class AjaxTicketList {

    private String message;
    private List<AjaxTicket> result;

    public void fillResult(List<Ticket> tickets) {
        result = new ArrayList<>();
        for (Ticket ticket : tickets) {
            AjaxTicket ticketData = new AjaxTicket(ticket);
            result.add(ticketData);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<AjaxTicket> getResult() {
        return result;
    }

    public void setResult(List<AjaxTicket> result) {
        this.result = result;
    }
}
