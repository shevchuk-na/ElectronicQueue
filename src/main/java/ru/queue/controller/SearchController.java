package ru.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.queue.domain.Queue;
import ru.queue.domain.User;
import ru.queue.service.QueueService;
import ru.queue.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;
    @Autowired
    private QueueService queueService;

    @RequestMapping("/searchQueue")
    public String searchByKeyword(@ModelAttribute("keyword") String keyword, Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        List<Queue> queuesFound = queueService.findByNameContaining(keyword);
        if(queuesFound.isEmpty()){
            model.addAttribute("noQueuesFound", true);
        } else {
            model.addAttribute("queuesFound", queuesFound);
        }
        model.addAttribute("user", user);
        return "searchQueue";
    }
}
