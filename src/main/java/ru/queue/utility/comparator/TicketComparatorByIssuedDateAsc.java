package ru.queue.utility.comparator;

import ru.queue.domain.Ticket;

import java.util.Comparator;

public class TicketComparatorByIssuedDateAsc implements Comparator<Ticket> {

    private static TicketComparatorByIssuedDateAsc instance = new TicketComparatorByIssuedDateAsc();

    private TicketComparatorByIssuedDateAsc() {
    }

    public static TicketComparatorByIssuedDateAsc getInstance() {
        return instance;
    }

    @Override
    public int compare(Ticket t1, Ticket t2) {
        return t1.getIssued().compareTo(t2.getIssued());
    }
}
