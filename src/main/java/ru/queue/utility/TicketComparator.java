package ru.queue.utility;

import ru.queue.domain.Ticket;

import java.util.Comparator;

public class TicketComparator implements Comparator<Ticket>{

    public static Comparator getTicketComparator(){
        return new TicketComparator();
    }

    @Override
    public int compare(Ticket t1, Ticket t2) {
        return t1.getIssued().compareTo(t2.getIssued());
    }
}
