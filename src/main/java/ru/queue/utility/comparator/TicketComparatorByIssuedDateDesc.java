package ru.queue.utility.comparator;

import ru.queue.domain.Ticket;

import java.util.Comparator;

public class TicketComparatorByIssuedDateDesc implements Comparator<Ticket> {

    private static TicketComparatorByIssuedDateDesc instance = new TicketComparatorByIssuedDateDesc();

    private TicketComparatorByIssuedDateDesc() {
    }

    public static TicketComparatorByIssuedDateDesc getInstance() {
        return instance;
    }

    @Override
    public int compare(Ticket t1, Ticket t2) {
        return -t1.getIssued().compareTo(t2.getIssued());
    }
}
