package ru.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.queue.domain.Comment;
import ru.queue.domain.Queue;
import ru.queue.domain.Ticket;
import ru.queue.domain.User;
import ru.queue.service.CommentService;
import ru.queue.service.QueueService;
import ru.queue.service.TicketService;
import ru.queue.service.UserService;
import ru.queue.utility.comparator.CommentComparatorByCreatedDateAsc;
import ru.queue.utility.comparator.QueueComparatorByCreatedDateAsc;
import ru.queue.utility.comparator.QueueComparatorByCreatedDateDesc;
import ru.queue.utility.comparator.TicketComparatorByIssuedDateAsc;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/queues")
public class QueueController {

    @Autowired
    private UserService userService;
    @Autowired
    private QueueService queueService;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("")
    public String queues(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        fillRelativePositions(user);
        user.getUserTicketList().sort(TicketComparatorByIssuedDateAsc.getInstance());
        user.getUserQueueAdminList().sort(QueueComparatorByCreatedDateAsc.getInstance());
        List<Queue> latestQueueList = queueService.findLastTen();
        latestQueueList.sort(QueueComparatorByCreatedDateDesc.getInstance());
        model.addAttribute("user", user);
        model.addAttribute("latestQueueList", latestQueueList);
        return "queues";
    }

    void fillRelativePositions(User user) {
        for (Ticket ticket : user.getUserTicketList()) {
            if (ticket.isActive()) {
                Queue currentQueue = queueService.findById(ticket.getQueue().getId());
                for (int i = 0; i < currentQueue.getTicketList().size(); i++) {
                    if (currentQueue.getTicketList().get(i).getUser().getUsername().equals(ticket.getUser().getUsername())) {
                        ticket.setRelativePosition(i + 1);
                        break;
                    }
                }
            }
        }
    }

    @RequestMapping("/newQueue")
    public String newQueue(Model model, Principal principal) {
        return "newQueue";
    }

    @RequestMapping(value = "/newQueue", method = RequestMethod.POST)
    public String newQueuePost(HttpServletRequest request,
                               @ModelAttribute("name") String name,
                               @ModelAttribute("description") String description,
                               @ModelAttribute("timeout") int timeout,
                               Model model,
                               Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Queue newQueue = queueService.findByName(name);
        if (newQueue != null) {
            name = name + ":(1)";
        }
        newQueue = new Queue();
        newQueue.setName(name);
        newQueue.setDescription(description);
        newQueue.setQueueAdmin(user);
        newQueue.setTicketsTotal(0);
        newQueue.setTimeout(timeout);
        newQueue.setCreated(LocalDateTime.now());
        newQueue = queueService.save(newQueue);
        model.addAttribute("queueCreated", true);
        model.addAttribute("queue", newQueue);
        return "newQueue";
    }

    @RequestMapping("/queue")
    public String queue(Model model, @RequestParam("id") Long id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Queue queue = queueService.findById(id);
        if (queue == null) {
            model.addAttribute("queueNotFound", true);
        } else {
            for (Ticket ticket : user.getUserTicketList()) {
                if (ticket.getQueue().equals(queue) && ticket.isActive()) {
                    model.addAttribute("userHasActiveTicket", true);
                    break;
                }
            }
            if (user.getUsername().equals(queue.getQueueAdmin().getUsername())) {
                model.addAttribute("userIsAdmin", true);
            } else {
                model.addAttribute("userIsAdmin", false);
            }
            queue.getComments().sort(CommentComparatorByCreatedDateAsc.getInstance());
            model.addAttribute("queue", queue);
            List<Ticket> activeTicketList = new ArrayList<>();
            if (queue.getTicketList().size() > 1) {
                activeTicketList = queue.getTicketList().subList(queue.getTicketList().size() - 1, queue.getTicketList().size());
            }
            model.addAttribute("activeTicketList", activeTicketList);
        }
        model.addAttribute("user", user);
        return "queue";
    }

    @RequestMapping(value = "/newTicket", method = RequestMethod.POST)
    public String newTicketPost(Model model, @RequestParam("id") String id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Queue queue = queueService.findById(Long.parseLong(id));
        boolean userHasActiveTicket = false;
        for (Ticket ticket : user.getUserTicketList()) {
            if (ticket.getQueue().equals(queue)) {
                userHasActiveTicket = true;
                break;
            }
        }
        if (!userHasActiveTicket) {
            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setQueue(queue);
            ticket.setActive(true);
            ticket.setIssued(LocalDateTime.now());
            queue.setTicketsTotal(queue.getTicketsTotal() + 1);
            ticket = ticketService.save(ticket);
            queue = queueService.save(queue);
        }
        return "redirect:queue?id=" + queue.getId();
    }

    @RequestMapping(value = "/nextTicket", method = RequestMethod.POST)
    public String nextTicketPost(Model model, @RequestParam("id") String id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Queue queue = queueService.findById(Long.parseLong(id));
        if (!queue.getQueueAdmin().getUsername().equals(user.getUsername())) {
            model.addAttribute("unauthorized", true);
        } else {
            queue.getTicketList().sort(TicketComparatorByIssuedDateAsc.getInstance());
            Ticket ticket = queue.getTicketList().get(0);
            ticketService.delete(ticket);
        }
        return "redirect:queue?id=" + queue.getId();
    }

    @RequestMapping(value = "/newComment", method = RequestMethod.POST)
    public String newCommentPost(Model model, @ModelAttribute("id") String id, @ModelAttribute("commentText") String commentText, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Queue queue = queueService.findById(Long.parseLong(id));
        Comment newComment = new Comment();
        newComment.setAuthor(user);
        newComment.setQueue(queue);
        newComment.setText(commentText);
        newComment.setCreated(LocalDateTime.now());
        newComment.setApproved(false);
        newComment = commentService.save(newComment);
        return "redirect:queue?id=" + queue.getId();
    }

    @RequestMapping(value = "/editComment")
    public String editComment(Model model, @RequestParam("id") String id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Comment comment = commentService.findById(Long.parseLong(id));
        if (!user.getId().equals(comment.getAuthor().getId())) {
            model.addAttribute("unauthorized", true);
        } else {
            model.addAttribute("comment", comment);
            return "editComment";
        }
        return "redirect:queue?id=" + comment.getQueue().getId();
    }

    @RequestMapping(value = "editCommentPost")
    public String editCommentPost(Model model, @ModelAttribute("id") String id, @ModelAttribute("commentText") String commentText, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Comment comment = commentService.findById(Long.parseLong(id));
        if (!user.getId().equals(comment.getAuthor().getId())) {
            model.addAttribute("unauthorized", true);
        } else {
            if (!commentText.isEmpty()) {
                comment.setText(commentText);
                comment.setApproved(false);
                comment = commentService.save(comment);
            } else {

            }
        }
        return "redirect:queue?id=" + comment.getQueue().getId();
    }

    @RequestMapping(value = "deleteComment")
    public String deleteComment(Model model, @RequestParam("id") String id, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Comment comment = commentService.findById(Long.parseLong(id));
        if (!user.getId().equals(comment.getAuthor().getId())) {
            model.addAttribute("unauthorized", true);
        } else {
            commentService.delete(comment);
        }
        return "redirect:queue?id=" + comment.getQueue().getId();
    }
}
