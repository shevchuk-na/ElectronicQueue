package ru.queue.utility;

import ru.queue.domain.Ticket;

import java.util.Comparator;

public class TicketComparatorByIssuedDate implements Comparator<Ticket>{

    public static Comparator getTicketComparator(){
        return new TicketComparatorByIssuedDate();
    }

    @Override
    public int compare(Ticket t1, Ticket t2) {
        return t1.getIssued().compareTo(t2.getIssued());
    }
}
