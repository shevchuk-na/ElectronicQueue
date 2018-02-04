package ru.queue.utility.comparator;

import ru.queue.domain.Queue;

import java.util.Comparator;

public class QueueComparatorByCreatedDateAsc implements Comparator<Queue> {

    private static QueueComparatorByCreatedDateAsc instance = new QueueComparatorByCreatedDateAsc();

    private QueueComparatorByCreatedDateAsc() {
    }

    public static QueueComparatorByCreatedDateAsc getInstance() {
        return instance;
    }

    @Override
    public int compare(Queue o1, Queue o2) {
        return o1.getCreated().compareTo(o2.getCreated());
    }
}
