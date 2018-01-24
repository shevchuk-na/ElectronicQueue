package ru.queue.utility;

import ru.queue.domain.Comment;

import java.util.Comparator;

public class CommentComparatorByCreatedDate implements Comparator<Comment> {

    private static CommentComparatorByCreatedDate instance = new CommentComparatorByCreatedDate();

    public static CommentComparatorByCreatedDate getInstance() {
        return instance;
    }

    @Override
    public int compare(Comment o1, Comment o2) {
        return o1.getCreated().compareTo(o2.getCreated());
    }
}
