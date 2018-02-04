package ru.queue.utility.comparator;

import ru.queue.domain.Comment;

import java.util.Comparator;

public class CommentComparatorByCreatedDateAsc implements Comparator<Comment> {

    private static CommentComparatorByCreatedDateAsc instance = new CommentComparatorByCreatedDateAsc();

    private CommentComparatorByCreatedDateAsc() {
    }

    public static CommentComparatorByCreatedDateAsc getInstance() {
        return instance;
    }

    @Override
    public int compare(Comment o1, Comment o2) {
        return o1.getCreated().compareTo(o2.getCreated());
    }
}
