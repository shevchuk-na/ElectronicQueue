package ru.queue.utility;

import ru.queue.domain.Queue;

import java.util.Comparator;

public class QueueComparatorByCreatedDateDesc implements Comparator<Queue> {

    private static QueueComparatorByCreatedDateDesc instance = new QueueComparatorByCreatedDateDesc();

    public static QueueComparatorByCreatedDateDesc getInstance() {
        return instance;
    }

    @Override
    public int compare(Queue o1, Queue o2) {
        return -o1.getCreated().compareTo(o2.getCreated());
    }
}
