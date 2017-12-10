package ru.queue.utility;

import ru.queue.domain.Queue;

import java.util.Comparator;

public class QueueComparatorByCreatedDate implements Comparator<Queue> {

    @Override
    public int compare(Queue o1, Queue o2) {
        return o1.getCreated().compareTo(o2.getCreated());
    }
}
