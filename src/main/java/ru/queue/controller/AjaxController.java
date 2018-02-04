package ru.queue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.queue.domain.User;
import ru.queue.domain.ajax.AjaxTicketList;
import ru.queue.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

    @Autowired
    private UserService userService;
    @Autowired
    private QueueController queueController;

    @RequestMapping("/getTicketUpdate")
    public ResponseEntity<?> getUserUpdate(Principal principal) {
        if (principal != null) {
            User user = userService.findByUsername(principal.getName());
            queueController.fillRelativePositions(user);
            AjaxTicketList ajaxTicketList = new AjaxTicketList();
            ajaxTicketList.setMessage("success");
            ajaxTicketList.fillResult(user.getUserTicketList());
            return ResponseEntity.ok(ajaxTicketList);
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }
}
